package simulation;

import simulation.models.Creature;

import java.util.*;

public class PathFinder {
    private final GameBoard board;
    public PathFinder(GameBoard board){
        this.board = board;
    }

    public List<Coordinates> bfs(GameBoard board, Creature creature){
        List<Coordinates> coordinatesForMoving = Collections.emptyList();

        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.getHeight()][board.getWidth()];
        Map<Coordinates, Coordinates> distanceToTarget = new HashMap<>();
        Coordinates start = creature.getCoordinates();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            if (visited[current.getX()][current.getY()] == true) {//если была посещена ранее, пропускаем итерацию
                continue;
            }
            visited[current.getX()][current.getY()] = true;//помечаем посещённой
            if (!current.equals(start)) {//если это не стартовая координата, откуда будет строиться путь
                if (board.isFood(current, creature)) {
                    coordinatesForMoving = reconstructPath(distanceToTarget, current);
                    break;
                }
                if (!board.isCellEmpty(current)) {//если текущая координата не пустая - пропускаем её
                    continue;
                }
            }
            addPredecessors(current, visited, queue, distanceToTarget, board);
        }
        return coordinatesForMoving;

    }

    private void addPredecessors(Coordinates current, boolean[][] visited, Queue<Coordinates> queue, Map<Coordinates, Coordinates> distanceToTarget, GameBoard board) {
        Coordinates[] directions = this.getDirections();
        for (Coordinates coordinates : directions) {
            Coordinates newCoordinates = new Coordinates(current.getX() + coordinates.getX(), current.getY() + coordinates.getY());
            if (GameBoard.isCoordinatesValid(newCoordinates, board.getHeight(), board.getWidth())) {//если не выходим за поле
                if (!visited[newCoordinates.getX()][newCoordinates.getY()] && !queue.contains(newCoordinates)) {
                    queue.add(newCoordinates);
                    distanceToTarget.put(newCoordinates, current);
                }
            }
        }
    }

    private Coordinates[] getDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }

    private LinkedList<Coordinates> reconstructPath(Map<Coordinates, Coordinates> traveledDistance, Coordinates target) {
        LinkedList<Coordinates> shortWay = new LinkedList<>();
        Coordinates current = target;

        while (current != null) {
            shortWay.addFirst(current);
            current = traveledDistance.get(current);
        }
        shortWay.removeFirst();//отрефачить, чтобы исходная клетка, где находится заяц, не помещался в очередь
        return shortWay;
    }
}
