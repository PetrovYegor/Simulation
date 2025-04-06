package simulation.actions.turn_actions;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.PathFinder;
import simulation.actions.Action;
import simulation.models.Creature;
import simulation.models.Entity;

import java.util.*;

public class MakeMoveAction implements Action {
    private final GameBoard board;

    public MakeMoveAction(GameBoard board) {
        this.board = board;
    }

    @Override
    public void execute() {
        Set<Coordinates> takenCoordinates = new HashSet<>(board.getTakenCoordinates());
        for (Coordinates c : takenCoordinates){
            if (!board.isCoordinatesEmpty(c)){
                Creature creature = (Creature) board.getEntity(c);
                creature.makeMove(board);
            }
        }
    }
}
