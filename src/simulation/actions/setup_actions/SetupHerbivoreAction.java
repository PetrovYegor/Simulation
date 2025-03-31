package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Herbivore;

public class SetupHerbivoreAction implements Action {
    private final GameBoard board;

    public SetupHerbivoreAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
//        for (int i = 0; i < EntityLimitSettings.DEER_LIMIT; i++) {
//            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
//            int x = randomFreeCoordinates.getX();
//            int y = randomFreeCoordinates.getY();
//            board.setEntity(new Coordinates(x, y), new Herbivore(new Coordinates(x, y), Creature.getRandomSpeed(), Creature.getRandomHealth()));
//        }
        board.setEntity(new Coordinates(2, 1), new Herbivore(new Coordinates(2, 1), 2, 2));
    }
}
