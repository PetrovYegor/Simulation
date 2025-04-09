package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;

import java.util.List;

public class Main {
    private static final int GAMEBOARD_HEIGHT = 7;
    private static final int GAMEBOARD_WIDTH = 10;

    public static void main(String[] args) {
        GameBoard board = new GameBoard(GAMEBOARD_HEIGHT, GAMEBOARD_WIDTH);
        List<Action> initActions = List.of(
                new SetupPredatorAction(board),
                new SetupHerbivoreAction(board),
                new SetupGrassAction(board),
                new SetupRockAction(board),
                new SetupTreeAction(board)
        );
        List<Action> turnActions = List.of(
                new AddHerbivoreAction(board),
                new AddGrassAction(board),
                new MakeMoveAction(board)
        );

        Simulation simulation = new Simulation(board, initActions, turnActions);
        simulation.printAndProcessMenu();
    }
}