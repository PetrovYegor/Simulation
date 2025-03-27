package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Creature;
import simulation.models.Deer;

public class SetupDeerAction implements Action {
    private final GameBoard board;

    public SetupDeerAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < EntityLimitSettings.DEER_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getFreeCoordinates();
            int x = randomFreeCoordinates.getX();
            int y = randomFreeCoordinates.getY();
            board.setEntity(new Coordinates(x, y), new Deer(new Coordinates(x, y), Creature.getRandomSpeed(), Creature.getRandomHealth()));
        }
    }
}
