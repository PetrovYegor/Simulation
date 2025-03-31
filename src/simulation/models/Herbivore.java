package simulation.models;


import simulation.Coordinates;
import simulation.GameBoard;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }
    public void attack(Coordinates target, GameBoard board) {
        eat(target, board);
    }

}
