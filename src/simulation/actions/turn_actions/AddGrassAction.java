package simulation.actions.turn_actions;

import simulation.BoardUtils;
import simulation.models.GameBoard;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.actions.setup_actions.SpawnAction;
import simulation.models.Grass;

public class AddGrassAction extends Action {
    private final GameBoard board;

    public AddGrassAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        if (!BoardUtils.isGrassEnough(board)) {
            SpawnAction<Grass> grassSpawnAction = new SpawnAction<>(board, ActionUtils.GRASS_LIMIT, Grass::new);
            grassSpawnAction.execute();
        }
    }
}
