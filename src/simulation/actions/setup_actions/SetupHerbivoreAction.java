package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.actions.ActionUtils;
import simulation.models.Herbivore;

public class SetupHerbivoreAction extends Action {
    private final GameBoard board;

    public SetupHerbivoreAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < ActionUtils.HERBIVORE_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            board.setEntity(randomFreeCoordinates, new Herbivore(randomFreeCoordinates, ActionUtils.getRandomSpeed(), ActionUtils.getRandomHealth()));
        }
    }
}
