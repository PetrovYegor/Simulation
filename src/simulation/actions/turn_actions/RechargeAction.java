package simulation.actions.turn_actions;

import simulation.actions.Action;
import simulation.actions.setup_actions.SpawnAction;
import simulation.models.Entity;
import simulation.models.GameBoard;

import java.util.List;
import java.util.function.Supplier;

public class RechargeAction<T extends Entity> extends Action {
    private GameBoard board;
    private Class<T> entityClazz;
    private Supplier<T> entitySupplier;
    private int upperLimit;
    public RechargeAction(GameBoard board, Class<T> entityClazz, Supplier<T> entitySupplier, int upperLimit){
        this.board = board;
        this.entityClazz = entityClazz;
        this.entitySupplier = entitySupplier;
        this.upperLimit = upperLimit;
    }
    @Override
    public void execute() {
        List<T> entities = board.getEntitiesBy(entityClazz);
        if (!isEnough(entities.size())){
            SpawnAction spawnAction = new SpawnAction(board, upperLimit, entitySupplier);
            spawnAction.execute();
        }
    }

    private boolean isEnough(int amount){
        return amount > upperLimit;
    }
}
