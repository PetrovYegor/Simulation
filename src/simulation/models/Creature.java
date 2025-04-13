package simulation.models;

import simulation.BoardUtils;
import simulation.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    private Coordinates coordinates;
    private final int speed;
    private int health;
    private final int ATTACK_DISTANCE = 1;

    protected Creature(Coordinates coordinates, int speed, int health) {
        this.coordinates = coordinates;
        this.speed = speed;
        this.health = health;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void makeMove(GameBoard board) {
        PathFinder pathFinder = new PathFinder(board);
        List<Coordinates> coordinatesForMoving = pathFinder.searchFood(getCoordinates(), getTypeOfFood());
        if (isFoodFound(coordinatesForMoving)) {
            if (canAttack(coordinatesForMoving)) {
                Coordinates target = coordinatesForMoving.get(0);
                attack(target, board);
            } else {
                move(coordinatesForMoving, board);
            }
        }
    }

    public abstract Class<? extends Entity> getTypeOfFood();

    public boolean canAttack(List<Coordinates> c) {
        return c.size() == ATTACK_DISTANCE;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public boolean isDead() {
        return health <= 0;
    }

    public abstract void attack(Coordinates target, GameBoard board);

    public void eat(Coordinates coordinates, GameBoard board) {
        BoardUtils.validateCoordinates(board, coordinates);
        board.removeEntity(coordinates);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void move(List<Coordinates> coordinatesForMoving, GameBoard board) {
        int steps = 0;
        for (Coordinates currentCoordinates : coordinatesForMoving) {
            if (steps < getSpeed()) {
                if (isNear(coordinatesForMoving, steps)) {//если creature уже находится на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = getCoordinates();
                board.moveEntity(oldCoordinates, currentCoordinates);
                steps++;
            }
        }
    }

    public boolean isNear(List<Coordinates> coordinatesForMoving, int steps) {
        return steps == coordinatesForMoving.size() - 1;
    }

    private boolean isFoodFound(List<Coordinates> wayToFood) {
        return !wayToFood.isEmpty();
    }
}
