package simulation.actions.turn_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.PathFinder;
import simulation.actions.Action;
import simulation.models.Creature;

import java.util.List;

public class MakeMoveAction implements Action {
    private final GameBoard board;
    private final PathFinder pathFinder;

    public MakeMoveAction(GameBoard board) {
        this.board = board;
        pathFinder  = new PathFinder(board);
    }

    @Override
    public void execute() {
        List<Creature> creatures = board.getCertainEntities(Creature.class);
        for (Creature creature : creatures) {
            List<Coordinates> coordinatesForMoving = pathFinder.searchFood(creature);
            creature.makeMove(coordinatesForMoving, board);
        }
    }
}
