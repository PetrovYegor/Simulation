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
        Coordinates[] directions = getDirections();
        //чтобы реализовать обход препятствий, нужно ещё одну булеан двумерный массив иметь, где если в ячейке тру - то там препятсвие
        //добавить проверку координат, что там не тру

        //сделать 5х5 карту, в левом верхнем травоядное, правом нижнем траву
        //посмотреть, как без препятствий дойдёт до травы. Вставить на пути дерево, посмотреть, как будет идти с учётом препятствия
        Queue<Coordinates> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.getHeight()][board.getWidth()];//избавиться от хардкода
        Map<Coordinates, Coordinates> traveledDistance = new HashMap<>();
        queue.add(this.getCoordinates());
        while (!queue.isEmpty()) {
            Coordinates current = queue.poll();
            if (visited[current.getX()][current.getY()]) {//вынести в метод
                continue;
            }
            if (board.isFood(current, this)) {
                int a =123;
                LinkedList<Coordinates> coordinatesForMoving = reconstructPath(traveledDistance, current);
                moveToFood(coordinatesForMoving,board);
                //завершаем цикл
                //код движения в сторону травы, или формирования пути
                return;
            }
            visited[current.getX()][current.getY()] = true;
            if (board.isBarrier(current)){
                continue;
            }
            addPredecessors(current, visited, queue, traveledDistance, board);
            //System.out.println(queue);
        }
        System.out.println("Еда не нашлась");
    }

    private void addPredecessors(Coordinates current, boolean[][] visited, Queue<Coordinates> queue, Map<Coordinates, Coordinates> traveledDistance, GameBoard board){
        Coordinates[] directions = this.getDirections();
        for (Coordinates coordinates : directions) {
            Coordinates newCoordinates = new Coordinates(current.getX() + coordinates.getX(), current.getY() + coordinates.getY());
            if (GameBoard.isCoordinatesValid(newCoordinates, board.getHeight(), board.getWidth())) {//если не выходим за поле
                if (!visited[newCoordinates.getX()][newCoordinates.getY()]){
                    queue.add(newCoordinates);
                    traveledDistance.put(newCoordinates, current);
                }
            }
        }

    }

    private void moveToFood(LinkedList<Coordinates> destination, GameBoard gameBoard) {
        int steps = 0;

        for (var temp : destination){
            if (steps < destination.size() && steps < this.speed){
                Coordinates oldCoordinates = this.coordinates;
                gameBoard.setEntity(temp, this);
                gameBoard.removeEntity(oldCoordinates);
                this.coordinates = temp;
                steps++;
            }
        }
    }

    private LinkedList<Coordinates> reconstructPath(Map<Coordinates, Coordinates> traveledDistance, Coordinates target) {
        LinkedList<Coordinates> shortWay = new LinkedList<>();
        Coordinates current = target;

        while (current != null){
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


    public String getSprite() {
        return Sprite.HERBIVORE;
    }

    //нужен метод движения
    //съесть траву
}
