package simulation.actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.models.Grass;

public class SetupGrassAction implements Action{
    private final GameBoard board;

    public SetupGrassAction(GameBoard board){
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < EntityLimitSettings.GRASS_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getFreeCoordinates();
            int x = randomFreeCoordinates.getX();
            int y = randomFreeCoordinates.getY();
            board.setEntity(new Coordinates(x, y), new Grass(new Coordinates(x, y)));
        }
    }
}
