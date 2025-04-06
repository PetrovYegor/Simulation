package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final GameBoard board;
    //private int moveCounter = 0;
    private final GameBoardRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(GameBoard board) {
        this.board = board;
        renderer = new GameBoardRenderer(board);
        initActions = getInitActions();
        turnActions = getTurnActions();
    }

    private List<Action> getInitActions() {
        List<Action> result = new ArrayList<>();
        result.add(new SetupPredatorAction(board));
        result.add(new SetupHerbivoreAction(board));
        //result.add(new SetupGrassAction(board));
        //result.add(new SetupRockAction(board));
        //result.add(new SetupTreeAction(board));
        return result;
    }

    public List<Action> getTurnActions() {
        List<Action> result = new ArrayList<>();
        result.add(new AddHerbivoreAction(board));
        //result.add(new AddGrassAction(board));
        result.add(new MakeMoveAction(board));
        return result;
    }

    private void nextTurn() {
        renderer.render();
        for (Action action : turnActions){
            action.execute();
        }
//        moveCounter++;
    }

    void startSimulation() throws InterruptedException {
        for (Action action : initActions) {
            action.execute();
        }

        while (true) {
            nextTurn();
            Thread.sleep(1000);
        }
    }

    private void pauseSimulation() {

    }
}