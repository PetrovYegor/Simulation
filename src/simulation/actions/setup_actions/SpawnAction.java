package simulation.actions.setup_actions;

import simulation.BoardUtils;
import simulation.actions.Action;
import simulation.models.Coordinates;
import simulation.models.Entity;
import simulation.models.GameBoard;

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
            Coordinates randomFreeCoordinates = BoardUtils.getRandomFreeCoordinates(board, board.getHeight(), board.getWidth());
            Entity entity = entitySupplier.get();
            board.setEntity(randomFreeCoordinates, entity);
        }
    }
}
