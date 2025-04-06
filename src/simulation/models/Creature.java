package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;
import simulation.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    public final int speed;
    public int health;//сделать приватным после дебага
    private final int ATTACK_DISTANCE = 1;

    protected Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    //    public void makeMove(GameBoard board) {
//        if (canAttack(coordinatesForMoving)) {
//            Coordinates target = coordinatesForMoving.get(0);
//            attack(target, board);
//        } else {
//            move(coordinatesForMoving, board);
//        }
//    }
    public void makeMove(GameBoard board) {
        PathFinder pathFinder = new PathFinder(board);
        List<Coordinates> coordinatesForMoving = pathFinder.searchFood(getCoordinates());
        if (isFoodFound(coordinatesForMoving)){
            if (canAttack(coordinatesForMoving)) {
                Coordinates target = coordinatesForMoving.get(0);
                attack(target, board);
            } else {
                move(coordinatesForMoving, board);
            }
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

    public boolean isDead() {
        return health <= 0;
    }

    public abstract void attack(Coordinates target, GameBoard board);

    public void eat(Coordinates coordinates, GameBoard board) {
        board.validateCoordinates(coordinates, "eat");
        board.removeEntity(coordinates);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    private void move(List<Coordinates> coordinatesForMoving, GameBoard board) {
        int steps = 0;
        for (Coordinates currentCoordinates : coordinatesForMoving) {
            if (steps < getSpeed()) {
                if (steps == coordinatesForMoving.size() - 1) {//если creature находится на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = getCoordinates();
                board.moveEntity(oldCoordinates, currentCoordinates);
                steps++;
            }
        }
    }
    private boolean isFoodFound(List<Coordinates> wayToFood){
        return !wayToFood.isEmpty();
    }
}
