package simulation;

import simulation.models.Coordinates;
import simulation.models.Creature;
import simulation.models.GameBoard;

import java.util.*;

public class PathFinder {
    private final GameBoard board;
    List<Coordinates> visited;
    Map<Coordinates, Coordinates> cameFrom;
    Queue<Coordinates> bfsQueue;

    public PathFinder(GameBoard board) {
        this.board = board;
        visited = new ArrayList<>();
        cameFrom = new HashMap<>();
        bfsQueue = new LinkedList<>();
    }

    public List<Coordinates> searchFood(Coordinates start) {
        BoardUtils.validateCoordinates(board, start);
        List<Coordinates> result = Collections.emptyList();
        Creature currentCreature = (Creature) board.getEntity(start);
        bfsQueue.add(start);

        while (!isBfsQueueEmpty()) {
            Coordinates current = bfsQueue.poll();
            if (isVisited(current)) {
                continue;
            }
            markAsVisited(current);
            if (isBeginningOfSearch(current, start)) {
                addPredecessors(current);
                continue;
            }
            if (!board.isCoordinatesEmpty(current)) {
                if (BoardUtils.isFood(board, current, currentCreature)) {
                    result = reconstructPath(current);
                    break;
                }
                continue;
            }
            addPredecessors(current);
        }
        return result;
    }

    private void addPredecessors(Coordinates current) {
        BoardUtils.validateCoordinates(board, current);
        Coordinates[] directions = getShiftDirections();
        for (Coordinates coordinates : directions) {
            int newX = current.x() + coordinates.x();
            int newY = current.y() + coordinates.y();
            Coordinates newCoordinates = new Coordinates(newX, newY);
            if (BoardUtils.isCoordinatesValid(newCoordinates, board.getHeight(), board.getWidth())) {
                if (!isVisited(newCoordinates) && !bfsQueue.contains(newCoordinates)) {
                    bfsQueue.add(newCoordinates);
                    cameFrom.put(newCoordinates, current);
                }
            }
        }
    }

    private Coordinates[] getShiftDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }

    private LinkedList<Coordinates> reconstructPath(Coordinates target) {
        BoardUtils.validateCoordinates(board, target);
        LinkedList<Coordinates> wayToTarget = new LinkedList<>();
        Coordinates current = target;
        do {
            wayToTarget.addFirst(current);
            current = cameFrom.get(current);
        } while (current != null);
        wayToTarget.removeFirst();
        return wayToTarget;
    }

    private boolean isVisited(Coordinates target) {
        BoardUtils.validateCoordinates(board, target);
        return visited.contains(target);
    }

    private void markAsVisited(Coordinates c) {
        BoardUtils.validateCoordinates(board, c);
        visited.add(c);
    }

    private boolean isBeginningOfSearch(Coordinates current, Coordinates start) {
        BoardUtils.validateCoordinates(board, current);
        BoardUtils.validateCoordinates(board, start);
        return current.equals(start);
    }

    private boolean isBfsQueueEmpty() {
        return bfsQueue.isEmpty();
    }
}
