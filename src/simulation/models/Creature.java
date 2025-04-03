package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.List;

public abstract class Creature extends Entity {
    public final int speed;
    private int health;
    private static final int ATTACK_DISTANCE = 1;

    protected Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public void makeMove(List<Coordinates> coordinatesForMoving, GameBoard board) {
        if (canAttack(coordinatesForMoving)) {
            Coordinates target = coordinatesForMoving.get(0);
            attack(target, board);
        } else {
            moveToFood(coordinatesForMoving, board);
        }
    }

    public boolean canAttack(List<Coordinates> c) {
        return c.size() == ATTACK_DISTANCE;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public abstract void attack(Coordinates target, GameBoard board);

    public void eat(Coordinates coordinates, GameBoard board) {
        board.removeEntity(coordinates);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void moveToFood(List<Coordinates> coordinatesForMoving, GameBoard board) {
        int steps = 0;
        for (Coordinates newCoordinates : coordinatesForMoving) {
            if (steps < getSpeed()) {
                if (steps == coordinatesForMoving.size() - 1) {//если creature находится на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = getCoordinates();
                board.moveEntity(oldCoordinates, newCoordinates);
                steps++;
            }
        }
    }
}

