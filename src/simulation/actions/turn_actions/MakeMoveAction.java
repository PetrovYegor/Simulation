package simulation.actions.turn_actions;

import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Creature;

import java.util.HashSet;
import java.util.Set;

public class MakeMoveAction implements Action {
    private final GameBoard board;

    public MakeMoveAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        Set<Creature> livingCreatures = new HashSet<>(board.getCertainEntities(Creature.class));
        for (Creature creature : livingCreatures) {
            if (board.isExists(creature)) {
                creature.makeMove(board);
            }
        }
    }
}
