package simulation;

import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;
import simulation.models.*;

import java.util.List;

public class Main {
    private static final int GAMEBOARD_HEIGHT = 7;
    private static final int GAMEBOARD_WIDTH = 10;

    public static void main(String[] args) {
        GameBoard board = new GameBoard(GAMEBOARD_HEIGHT, GAMEBOARD_WIDTH);
        List<Action> initActions = List.of(
                new SpawnAction<>(board, ActionUtils.PREDATOR_LIMIT, () -> new Predator(board.getRandomFreeCoordinates(), ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth(), ActionUtils.getRandomAttackPower())),
                new SpawnAction<>(board, ActionUtils.HERBIVORE_LIMIT
                        , () -> new Herbivore(board.getRandomFreeCoordinates()
                        , ActionUtils.getRandomSpeed()
                        , ActionUtils.getRandomHealth())),
                new SpawnAction<>(board, ActionUtils.GRASS_LIMIT, Grass::new),
                new SpawnAction<>(board, ActionUtils.ROCK_LIMIT, Rock::new),
                new SpawnAction<Tree>(board, ActionUtils.TREE_LIMIT, Tree::new)
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