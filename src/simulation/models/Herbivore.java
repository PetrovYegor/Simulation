package simulation.models;
//Травоядное, наследуется от Creature. Стремятся найти ресурс (траву),
// может потратить свой ход на движение в сторону травы, либо на её поглощение.

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.Sprite;

import java.util.*;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    @Override
    public void makeMove(List<Coordinates> coordinatesForMoving, GameBoard board) {
        if (coordinatesForMoving.size() == 1) {//если стоим в одной клетке от еды, то не движемся к ней, мы её уже достигли
            eat(this.coordinates, board);
        } else {
            moveToFood(coordinatesForMoving, board);
        }
    }

    @Override
    public void moveToFood(List<Coordinates> coordinatesForMoving, GameBoard board) {
        if (coordinatesForMoving.isEmpty()) {
            return;
        }
        int steps = 0;//счётчик потраченных очков speed

        for (Coordinates newCoordinates : coordinatesForMoving) {
            if (steps < coordinatesForMoving.size() && steps < this.speed) {//пока количество сделанных шагов не превышает путь до цели и не кончились очки скорости
                if (steps == coordinatesForMoving.size() - 1) {//если мы уже находимся на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = this.coordinates;
                board.moveEntity(oldCoordinates, newCoordinates);
                steps++;
            }
        }
    }
}
