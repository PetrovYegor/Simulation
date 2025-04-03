package simulation.models;

import simulation.Coordinates;

public abstract class Predator extends Creature {
    private final int attackPower;

    public Predator(Coordinates coordinates, int speed, int health, int attackPower) {
        super(coordinates, speed, health);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

}
