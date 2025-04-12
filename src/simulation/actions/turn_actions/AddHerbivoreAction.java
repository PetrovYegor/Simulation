package simulation.actions.turn_actions;

import simulation.BoardUtils;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.actions.setup_actions.SpawnAction;
import simulation.models.GameBoard;
import simulation.models.Herbivore;

public class AddHerbivoreAction extends Action {
    private final GameBoard board;

    public AddHerbivoreAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        if (!BoardUtils.isHerbivoreEnough(board)) {
            SpawnAction<Herbivore> herbivoreSpawnAction = new SpawnAction<>(board, ActionUtils.HERBIVORE_LIMIT
                    , () -> new Herbivore(BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth())
                    , ActionUtils.getRandomSpeed()
                    , ActionUtils.getRandomHealth()));

            herbivoreSpawnAction.execute();
        }
    }
}
