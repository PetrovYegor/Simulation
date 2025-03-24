package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Deer extends Herbivore{
    public Deer(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }


    @Override
    public void makeMove(GameBoard board) {
        bfs(speed, board);
    }

    private void bfs(int speed, GameBoard board) {//убрать из параметров GameBoard
        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.getHeight()][board.getWidth()];//избавиться от хардкода
        Map<Coordinates, Coordinates> traveledDistance = new HashMap<>();
        Coordinates source = this.coordinates;
        queue.add(source);
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            if (visited[current.getX()][current.getY()] == true) {//если была посещена ранее, следующая итерация
                continue;
            }
            visited[current.getX()][current.getY()] = true;//помечаем посещённой
            if (!current.equals(source)) {//если очередная координата из очереди не совпадает с точкой старта
                if (board.isFood(current, this)) {
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


    private void addPredecessors(Coordinates current, boolean[][] visited, Queue<Coordinates> queue, Map<Coordinates, Coordinates> traveledDistance, GameBoard board) {
        Coordinates[] directions = this.getDirections();
        for (Coordinates coordinates : directions) {
            Coordinates newCoordinates = new Coordinates(current.getX() + coordinates.getX(), current.getY() + coordinates.getY());
            if (GameBoard.isCoordinatesValid(newCoordinates, board.getHeight(), board.getWidth())) {//если не выходим за поле
                if (!visited[newCoordinates.getX()][newCoordinates.getY()] && !queue.contains(newCoordinates)) {
                    queue.add(newCoordinates);
                    traveledDistance.put(newCoordinates, current);
                }
            }
        }
    }

    private void moveToFood(LinkedList<Coordinates> destination, GameBoard gameBoard) {
        int steps = 0;//счётчик потраченных очков speed

        for (var temp : destination) {
            if (steps < destination.size() && steps < this.speed) {//пока количество сделанных шагов не превышает путь до цели и не кончились очки скорости
                if (steps == destination.size() - 1) {//если мы уже находимся на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = this.coordinates;
                gameBoard.setEntity(temp, this);
                gameBoard.removeEntity(oldCoordinates);
                steps++;
            }
        }
    }

    private void eat(Coordinates c, GameBoard board) {
        board.removeEntity(c);
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

    private Coordinates[] getDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }
}
