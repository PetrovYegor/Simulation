package simulation;

import simulation.models.Entity;
import simulation.models.Grass;
import simulation.models.Herbivore;
import simulation.models.Predator;

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

    public boolean isFood(Coordinates coordinates, Entity entity){
        Entity targetEntity = entitiesByCoordinates.get(coordinates);
        if (targetEntity instanceof Grass && entity instanceof Herbivore){
            return true;
        }
        if (targetEntity instanceof Herbivore && entity instanceof Predator){
            return true;
        }
        return false;
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

    public static boolean isCoordinatesValid(Coordinates coordinates){
        int targetX = coordinates.getX();
        int targetY = coordinates.getY();
        boolean xValid = targetX >= 0 && targetX < 5;//!!!!!! убрать хардкод
        boolean yValid = targetY >= 0 && targetY < 5;//!!!!!! убрать хардкод
        return xValid && yValid;
    }


}

//заполнить размер поля через диалог?
//Карта, содержит в себе коллекцию для хранения существ и их расположения.
// Советую не спешить использовать двумерный массив или список списков,
// а подумать какие ещё коллекции могут подойти.