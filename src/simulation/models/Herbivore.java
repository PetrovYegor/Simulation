package simulation.models;
//Травоядное, наследуется от Creature. Стремятся найти ресурс (траву),
// может потратить свой ход на движение в сторону травы, либо на её поглощение.

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.Sprite;

import java.util.*;

public class Herbivore extends Creature {


//    public Herbivore(Coordinates coordinates, int speed, int health) {
//        super(coordinates, speed, health);
//    }

    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    @Override
    public void makeMove() {

    }


    @Override
    public void makeMove(GameBoard board) {
        bfs(speed, board);
    }

    //возможно добавить этому методу возвращение boolean, что не нашёл травы, тогда сработает метод генерации травы
    private void bfs(int speed, GameBoard board) {//убрать из параметров GameBoard
        int a = 123;
        //Map<Coordinates, Integer> cepochka_sosedej = new HashMap<>();
        //чтобы реализовать обход препятствий, нужно ещё одну булеан двумерный массив иметь, где если в ячейке тру - то там препятсвие
        //добавить проверку координат, что там не тру

        //сделать 5х5 карту, в левом верхнем травоядное, правом нижнем траву
        //посмотреть, как без препятствий дойдёт до травы. Вставить на пути дерево, посмотреть, как будет идти с учётом препятствия
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
                    if (moveToFood(coordinatesForMoving, board)){
                        eat(current, board);
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
            //System.out.println(queue);
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


    private boolean moveToFood(LinkedList<Coordinates> destination, GameBoard gameBoard) {
        Coordinates target = destination.getLast();

        if (destination.size() == 1) {//если стоим в одной клетке от еды, то не движемся к ней, мы её уже достигли
            return true;
        }

        int steps = 0;//счётчик потраченных очков speed

        for (var temp : destination) {
            if (steps < destination.size() && steps < this.speed) {//пока количество сделанных шагов не превышает путь до цели и не кончились очки скорости
                if (steps == destination.size() - 1){//если мы уже находимся на расстоянии одной клетки от еды
                    return true;
                }
                Coordinates oldCoordinates = this.coordinates;
                gameBoard.setEntity(temp, this);
                gameBoard.removeEntity(oldCoordinates);
                steps++;
            }
        }
        return false;
    }

    private void eat(Coordinates c, GameBoard board) {
        board.removeEntity(c);
    }

    public int getSpeed() {
        return speed;
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
        Coordinates[] directions = {
                new Coordinates(-1, 0),
                //new Coordinates(-1, 1),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
                //new Coordinates(1, 1),
                //new Coordinates(1, -1),
                //new Coordinates(-1, -1),
        };
        return directions;
    }


    //нужен метод движения
    //съесть траву
}
