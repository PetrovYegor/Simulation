package simulation;

import simulation.models.Creature;

import java.util.*;

public class PathFinder {
    private final GameBoard board;
    public PathFinder(GameBoard board){
        this.board = board;
    }

    private List<Coordinates> bfs(GameBoard board, Creature creature){
        List<Coordinates> result = Collections.emptyList();


        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.getHeight()][board.getWidth()];
        Map<Coordinates, Coordinates> distanceToTarget = new HashMap<>();
        Coordinates start = creature.getCoordinates();
        queue.add(start);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            if (visited[current.getX()][current.getY()] == true) {//если была посещена ранее, следующая итерация
                continue;
            }
            visited[current.getX()][current.getY()] = true;//помечаем посещённой
            if (!current.equals(start)) {//если очередная координата из очереди не совпадает с точкой старта
                if (board.isFood(current, creature)) {
                    LinkedList<Coordinates> coordinatesForMoving = reconstructPath(traveledDistance, current);
                    if (coordinatesForMoving.size() == 1) {//если стоим в одной клетке от еды, то не движемся к ней, мы её уже достигли
                        eat(current, board);
                    } else {
                        moveToFood(coordinatesForMoving, board);
                    }
                    //завершаем цикл
                    //код движения в сторону травы, или формирования пути
                    return;
                }
                if (!board.isCellEmpty(current)) {//если текущая координата не пустая - пропускаем её
                    continue;
                }
            }
            addPredecessors(current, visited, queue, traveledDistance, board);
        }
        System.out.println("Еда не нашлась");

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
