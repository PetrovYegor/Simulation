package simulation.actions.turn_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.PathFinder;
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
        List<Creature> creatures = board.getCertainEntities(Creature.class);
        for (Creature creature : creatures) {
            PathFinder pathFinder = new PathFinder(board);
            List<Coordinates> coordinatesForMoving = pathFinder.searchFood(creature.getCoordinates());
            if (isFoodFound(coordinatesForMoving)){
                creature.makeMove(coordinatesForMoving, board);
            }
        }
    }

    private boolean isFoodFound(List<Coordinates> c){
        return !c.isEmpty();
    }
}
