package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.List;

public class Predator extends Creature {
    private final int attackPower;

    public Predator(Coordinates coordinates, int speed, int health, int attackPower) {
        super(coordinates, speed, health);
        this.attackPower = attackPower;
    }

    @Override
    public void moveToFood(List<Coordinates> coordinatesForMoving, GameBoard board) {
        int steps = 0;
        for (Coordinates currentCoordinates : coordinatesForMoving) {
            if (steps < getSpeed()) {
                if (steps == coordinatesForMoving.size() - 1) {//если creature находится на расстоянии одной клетки от еды
                    attack(currentCoordinates, board);
                    return;
                }
                Coordinates oldCoordinates = getCoordinates();
                board.moveEntity(oldCoordinates, currentCoordinates);
                steps++;
            }
        }
    }

    public void attack(Coordinates target, GameBoard board) {
        Creature victim = (Creature) board.getEntity(target);
        int currentVictimHealth = victim.getHealth();
        victim.setHealth(currentVictimHealth - attackPower);
        if (victim.isDead()) {
            eat(target, board);
        }
    }
}
