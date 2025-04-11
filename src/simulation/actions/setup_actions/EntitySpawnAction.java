package simulation.actions.setup_actions;

import simulation.GameBoard;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.models.*;

public class EntitySpawnAction extends Action {
    private GameBoard board;

    public EntitySpawnAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        SpawnAction<Grass> grassSpawnAction = new SpawnAction<>(board, ActionUtils.GRASS_LIMIT, Grass::new);
        SpawnAction<Rock> rockSpawnAction = new SpawnAction<>(board, ActionUtils.ROCK_LIMIT, Rock::new);
        SpawnAction<Tree> treeSpawnAction = new SpawnAction<Tree>(board, ActionUtils.TREE_LIMIT, Tree::new);
        SpawnAction<Herbivore> herbivoreSpawnAction = new SpawnAction<>(board, ActionUtils.HERBIVORE_LIMIT
                , () -> new Herbivore(board.getRandomFreeCoordinates()
                                      , ActionUtils.getRandomSpeed()
                                      , ActionUtils.getRandomHealth()));
        SpawnAction<Predator> predatorSpawnAction = new SpawnAction<>(board, ActionUtils.PREDATOR_LIMIT, () -> new Predator(board.getRandomFreeCoordinates(), ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth(), ActionUtils.getRandomAttackPower()));

    }
}
