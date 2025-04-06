package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.EntityLimitSettings;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Creature;
import simulation.models.CreatureUtils;
import simulation.models.Herbivore;

public class SetupHerbivoreAction implements Action {
    private final GameBoard board;

    public SetupHerbivoreAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
//        for (int i = 0; i < EntityLimitSettings.HERBIVORE_LIMIT; i++) {
//            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
//            int x = randomFreeCoordinates.x();
//            int y = randomFreeCoordinates.y();
//            board.setEntity(new Coordinates(x, y), new Herbivore(new Coordinates(x, y), CreatureUtils.getRandomSpeed(), CreatureUtils.getRandomHealth()));
//        }
        board.setEntity(new Coordinates(2, 2), new Herbivore(new Coordinates(2, 2), CreatureUtils.getRandomSpeed(), 1));
        board.setEntity(new Coordinates(4, 3), new Herbivore(new Coordinates(4, 3), CreatureUtils.getRandomSpeed(),1));
    }
}
