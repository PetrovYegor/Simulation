package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.CreatureUtils;
import simulation.models.Predator;

public class SetupPredatorAction implements Action {
    private final GameBoard board;

    public SetupPredatorAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        for (int i = 0; i < EntityLimitSettings.PREDATOR_LIMIT; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            int x = randomFreeCoordinates.x();
            int y = randomFreeCoordinates.y();
            board.setEntity(new Coordinates(x, y), new Predator(new Coordinates(x, y), CreatureUtils.getRandomSpeed(), CreatureUtils.getRandomHealth(), CreatureUtils.getRandomAttackPower()));
        }
    }
}