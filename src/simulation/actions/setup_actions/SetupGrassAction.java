package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Grass;

public class SetupGrassAction implements Action {
    private final GameBoard board;

    public SetupGrassAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
//        for (int i = 0; i < EntityLimitSettings.GRASS_LIMIT; i++) {
//            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
//            int x = randomFreeCoordinates.getX();
//            int y = randomFreeCoordinates.getY();
//            board.setEntity(new Coordinates(x, y), new Grass(new Coordinates(x, y)));
//        }
        board.setEntity(new Coordinates(0, 1), new Grass(new Coordinates(0, 1)));
    }
}
