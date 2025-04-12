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

    public static void main(String[] args) {
        GameBoard board = new GameBoard(GAMEBOARD_HEIGHT, GAMEBOARD_WIDTH);
        List<Action> initActions = List.of(
                new SpawnAction<>(board, ActionUtils.PREDATOR_LIMIT, () -> new Predator(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth()), ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth(), ActionUtils.getRandomAttackPower())),
                new SpawnAction<>(board, ActionUtils.HERBIVORE_LIMIT
                        , () -> new Herbivore(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth())
                        , ActionUtils.getRandomSpeed()
                        , ActionUtils.getRandomHealth())),
                new SpawnAction<>(board, ActionUtils.GRASS_LIMIT, Grass::new),
                new SpawnAction<>(board, ActionUtils.ROCK_LIMIT, Rock::new),
                new SpawnAction<Tree>(board, ActionUtils.TREE_LIMIT, Tree::new)
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