package simulation.actions.setup_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Entity;

import java.util.function.Supplier;

public class SpawnAction<T extends Entity> extends Action {
    private final Supplier<Entity> entitySupplier;
    private final int amount;
    private final GameBoard board;

    public SpawnAction(GameBoard board, int amount, Supplier<Entity> entitySupplier) {
        this.board = board;
        this.amount = amount;
        this.entitySupplier = entitySupplier;
    }

    @Override
    public void execute() {
        for (int i = 0; i < amount; i++) {
            Coordinates randomFreeCoordinates = board.getRandomFreeCoordinates();
            Entity entity = entitySupplier.get();
            board.setEntity(randomFreeCoordinates, entity);
        }
    }
}
