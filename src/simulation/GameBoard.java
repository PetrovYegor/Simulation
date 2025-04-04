package simulation;

import simulation.models.Creature;
import simulation.models.Entity;
import simulation.models.Grass;
import simulation.models.Herbivore;

import java.util.*;

public class GameBoard {
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> entities;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
        this.entities = new HashMap<>();
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public <T extends Entity> List<T> getCertainEntities(Class<T> entityType) {
        List<T> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entityType.isInstance(entity)) {
                result.add(entityType.cast(entity));
            }
        }
        return result;
    }

    public Coordinates getRandomFreeCoordinates() {
        Set<Coordinates> allCoordinates = getAllGameBoardCoordinates();
        for (Coordinates c : allCoordinates) {
            if (isCoordinatesEmpty(c)) {
                return c;
            }
        }
        throw new IllegalStateException("The game board cannot be without free cells!");
    }

    private Set<Coordinates> getAllGameBoardCoordinates() {
        Set<Coordinates> result = new HashSet<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.add(new Coordinates(i, j));
            }
        }
        return result;
    }

    public boolean isCoordinatesEmpty(Coordinates c) {
        return !entities.containsKey(c);
    }

    public boolean isCoordinatesValid(Coordinates coordinates) {//это лучше в класс координат перенести
        int targetX = coordinates.x();
        int targetY = coordinates.y();
        boolean xValid = targetX >= 0 && targetX < getHeight();
        boolean yValid = targetY >= 0 && targetY < getWidth();
        return xValid && yValid;
    }

    public boolean isFood(Coordinates targetCoordinates, Creature creature) {
        Entity targetEntity = entities.get(targetCoordinates);
        if (isGrass(targetEntity) && isHerbivore(creature)) {
            return true;
        }
//        if (isHerbivore(targetEntity) && isPredator(creature)) {
//            return true;
//        }
        return false;
    }

    public boolean isGrass(Entity entity) {
        return entity instanceof Grass;
    }

    public boolean isHerbivore(Entity entity) {
        return entity instanceof Herbivore;
    }

//    public boolean isPredator(Entity entity) {
//        return entity instanceof Predator;
//    }
}
