package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.*;

public class Wolf extends Predator {
    public Wolf(Coordinates coordinates, int speed, int health, int attackPower) {
        super(coordinates, speed, health, attackPower);
    }

    @Override
    public void makeMove(List<Coordinates> coordinatesForMoving, GameBoard board) {
        if (coordinatesForMoving.size() == 1) {//если стоим в одной клетке от еды, то не движемся к ней, мы её уже достигли
            attackHerbivore((Herbivore)board.getEntity(coordinatesForMoving.get(0)), board);
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
                    attackHerbivore((Herbivore)board.getEntity(newCoordinates), board);
                    return;
                }
                Coordinates oldCoordinates = this.coordinates;
                board.moveEntity(oldCoordinates, newCoordinates);
                steps++;
            }
        }
    }

    private void attackHerbivore(Herbivore h, GameBoard board) {
        int currentHerbivoreHealth = h.getHealth();
        currentHerbivoreHealth -= this.getAttackPower();
        if (isDead(currentHerbivoreHealth)) {
            eat(h.coordinates, board);
        } else {
            h.setHealth(currentHerbivoreHealth);
        }
    }

    private boolean isDead(int currentHerbivoreHealth) {
        return currentHerbivoreHealth <= 0 ? true : false;
    }
}
