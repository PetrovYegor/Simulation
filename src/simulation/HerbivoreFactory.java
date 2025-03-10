package simulation;

import simulation.models.Coordinate;
import simulation.models.Herbivore;

public class HerbivoreFactory {
    public static Herbivore createHerbivore(int speed, int health, Coordinate coordinate){
        return new Herbivore(speed, health, coordinate);
    }
}
