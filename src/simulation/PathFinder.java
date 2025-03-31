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
            }
            addPredecessors(current);
        }
        return result;
    }

    private void addPredecessors(Coordinates current) {
        Coordinates[] directions = getShiftDirections();
        for (Coordinates coordinates : directions) {
            int newX = current.getX() + coordinates.getX();
            int newY = current.getY() + coordinates.getY();
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
        return visited[target.getX()][target.getY()];
    }

    private void markAsVisited(Coordinates c) {
        visited[c.getX()][c.getY()] = true;
    }

    private boolean isBeginningOfSearch(Coordinates current, Coordinates start) {
        return current.equals(start);
    }

    private boolean isBfsQueueEmpty() {
        return bfsQueue.isEmpty();
    }
}
