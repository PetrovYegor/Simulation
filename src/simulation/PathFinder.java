package simulation;

import simulation.models.Creature;

import java.util.*;

public class PathFinder {
    private final GameBoard board;
    boolean[][] visited;
    Map<Coordinates, Coordinates> cameFrom;
    Queue<Coordinates> bfsQueue;

    public PathFinder(GameBoard board) {
        this.board = board;
        visited = new boolean[board.getHeight()][board.getWidth()];
        cameFrom = new HashMap<>();
        bfsQueue = new LinkedList<>();
    }

    public List<Coordinates> searchFood(Coordinates start) {
        board.validateCoordinates(start, "searchFood");
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
                if (board.isFood(current, currentCreature)) {
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
        board.validateCoordinates(current, "addPredecessors");
        Coordinates[] directions = getShiftDirections();
        for (Coordinates coordinates : directions) {
            int newX = current.x() + coordinates.x();
            int newY = current.y() + coordinates.y();
            Coordinates newCoordinates = new Coordinates(newX, newY);
            if (board.isCoordinatesValid(newCoordinates)) {
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
        board.validateCoordinates(target, "reconstructPath");
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
        board.validateCoordinates(target, "isVisited");
        return visited[target.x()][target.y()];
    }

    private void markAsVisited(Coordinates c) {
        board.validateCoordinates(c, "markAsVisited");
        visited[c.x()][c.y()] = true;
    }

    private boolean isBeginningOfSearch(Coordinates current, Coordinates start) {
        board.validateCoordinates(current, "isBeginningOfSearch (current)");
        board.validateCoordinates(start, "isBeginningOfSearch (start)");
        return current.equals(start);
    }

    private boolean isBfsQueueEmpty() {
        return bfsQueue.isEmpty();
    }
}
