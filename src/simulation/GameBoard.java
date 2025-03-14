package simulation;

import simulation.models.Entity;
import simulation.models.Herbivore;

import java.util.*;

public class GameBoard {
    private static final int HERBIVORE_LIMIT = 2;
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> entitiesByCoordinates;

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
        this.entitiesByCoordinates = new HashMap<>();
    }

    //установить фигуру на игровой карте
    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entitiesByCoordinates.put(coordinates, entity);
    }

    //создаём фигуры на игровой карте
    public void setupHerbivoresPositions() {
        for (int i = 0; i < HERBIVORE_LIMIT; i++) {
            setEntity(new Coordinates(1, 1), new Herbivore(new Coordinates(1, 1), 4, 3));
            //setEntity(new Coordinates(3, 3), new Herbivore(new Coordinates(3, 3), 2, 5));
        }
    }

    public boolean isCellEmpty(Coordinates coordinates) {//метод для рендера
        return !entitiesByCoordinates.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates){//метод для рендера
        return entitiesByCoordinates.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates){
        entitiesByCoordinates.remove(coordinates);
    }
    public List<Entity> getAllEntities(){
        return new ArrayList<>(entitiesByCoordinates.values());
    }



    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}

//заполнить размер поля через диалог?
//Карта, содержит в себе коллекцию для хранения существ и их расположения.
// Советую не спешить использовать двумерный массив или список списков,
// а подумать какие ещё коллекции могут подойти.