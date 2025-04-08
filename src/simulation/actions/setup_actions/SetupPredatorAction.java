package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.models.Predator;

public class SetupPredatorAction extends Action {
    private final GameBoard board;

    public SetupPredatorAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < ActionUtils.PREDATOR_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            board.setEntity(randomFreeCoordinates, new Predator(randomFreeCoordinates, ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth(), ActionUtils.getRandomAttackPower()));
        }
    }
}