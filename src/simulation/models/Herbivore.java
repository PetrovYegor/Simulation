package simulation.models;


import simulation.BoardUtils;

public class Herbivore extends Creature {
    public Herbivore(Coordinates coordinates, int speed, int health) {
        super(coordinates, speed, health);
    }

    public void attack(Coordinates target, GameBoard board) {
        BoardUtils.validateCoordinates(board, target);
        eat(target, board);
    }

    @Override
    public Class<? extends Entity> getTypeOfFood() {
        return Grass.class;
    }
}
