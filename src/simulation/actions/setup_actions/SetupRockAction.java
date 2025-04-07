package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Rock;

public class SetupRockAction implements Action {
    private final GameBoard board;

    public SetupRockAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < EntityLimitSettings.ROCK_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            int x = randomFreeCoordinates.x();
            int y = randomFreeCoordinates.y();
            board.setEntity(new Coordinates(x, y), new Rock());
        }
    }
}
