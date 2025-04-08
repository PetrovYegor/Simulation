package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final String ITERATION_COUNT_MESSAGE = "The number of iterations of the game cycle: %d";
    private final GameBoard board;
    private int moveCounter = 1;
    private final GameBoardRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private boolean isThreadStopped = false;
    private boolean wasInitializedEarlier = false;

    public Simulation(GameBoard board, List<Action> initActions, List<Action> turnActions) {
        this.board = board;
        renderer = new GameBoardRenderer(board);
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    private void nextTurn() {
        System.out.printf(ITERATION_COUNT_MESSAGE, moveCounter);
        renderer.render();
        for (Action action : turnActions) {
            action.execute();
        }
        moveCounter++;
    }

    void startSimulation() throws InterruptedException {
        if (!wasInitializedEarlier) {
            for (Action action : initActions) {
                action.execute();
            }
            wasInitializedEarlier = true;
        }
        Thread test = new Thread(new Runnable() {//переименовать переменную
            @Override
            public void run() {
                while (true) {
                    if (!isThreadStopped) {
                        nextTurn();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        test.start();
    }

    public void pauseSimulation() {
        System.out.println("Simulation was stopped");//вынести сообщения в константы
        isThreadStopped = true;
    }

    public void resumeSimulation() throws InterruptedException {
        System.out.println("Simulation was resumed");
        isThreadStopped = false;
        startSimulation();
    }
}