package simulation;

import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.actions.setup_actions.SpawnAction;
import simulation.actions.turn_actions.MakeMoveAction;
import simulation.actions.turn_actions.RechargeAction;
import simulation.models.*;

import java.util.List;

public class Main {
    private static final int GAMEBOARD_HEIGHT = 7;
    private static final int GAMEBOARD_WIDTH = 10;
    private static final int GRASS_GENERATE_VALUE = 3;
    private static final int HERBIVORE_GENERATE_VALUE = 2;
    private static final int HERBIVORE_LIMIT = 3;
    private static final int GRASS_LIMIT = 5;
    private static final int ROCK_LIMIT = 3;
    private static final int PREDATOR_LIMIT = 1;
    private static final int TREE_LIMIT = 5;

    public static void main(String[] args) {
        GameBoard board = new GameBoard(GAMEBOARD_HEIGHT, GAMEBOARD_WIDTH);
        List<Action> initActions = List.of(
                new SpawnAction<>(board, PREDATOR_LIMIT, () -> new Predator(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth()), ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth(), ActionUtils.getRandomAttackPower())),
                new SpawnAction<>(board, HERBIVORE_LIMIT
                        , () -> new Herbivore(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth())
                        , ActionUtils.getRandomSpeed()
                        , ActionUtils.getRandomHealth())),
                new SpawnAction<>(board, GRASS_LIMIT, Grass::new),
                new SpawnAction<>(board, ROCK_LIMIT, Rock::new),
                new SpawnAction<Tree>(board, TREE_LIMIT, Tree::new)
        );
        List<Action> turnActions = List.of(
                new RechargeAction<Grass>(board, Grass.class, Grass::new, GRASS_GENERATE_VALUE),
                new RechargeAction<Herbivore>(board, Herbivore.class, () -> new Herbivore(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth()), ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth()), HERBIVORE_GENERATE_VALUE),
                new MakeMoveAction(board)
        );

        Simulation simulation = new Simulation(board, initActions, turnActions);
        simulation.printAndProcessMenu();
    }
}