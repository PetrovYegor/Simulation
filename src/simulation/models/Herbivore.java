package simulation.models;


public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    public void attack(Coordinates target, GameBoard board) {
        board.validateCoordinates(target, "attack");
        eat(target, board);
    }
}
