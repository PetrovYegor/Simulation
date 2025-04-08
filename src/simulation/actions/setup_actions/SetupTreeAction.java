package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.models.Tree;

public class SetupTreeAction extends Action {
    private final GameBoard board;

    public SetupTreeAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < ActionUtils.TREE_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            board.setEntity(randomFreeCoordinates, new Tree());
        }
    }
}