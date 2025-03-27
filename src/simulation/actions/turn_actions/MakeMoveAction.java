package simulation.actions.turn_actions;

import simulation.GameBoard;
import simulation.actions.Action;
import simulation.models.Creature;

import java.util.List;

public class MakeMoveAction implements Action {
    private final GameBoard board;

    public MakeMoveAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        List<Creature> creatures = board.getCreatures();
        for (Creature creature : creatures) {
            creature.makeMove();
        }
    }
}
