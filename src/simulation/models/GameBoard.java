package simulation.models;

import simulation.BoardUtils;

import java.util.*;

public class GameBoard {
    private final int height;
    private final int width;
    private final Map<Coordinates, Entity> entities = new HashMap<>();

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        BoardUtils.validateCoordinates(this, coordinates);
        if (entity == null) {
            throw new IllegalArgumentException("The enitity can not be null!");
        }
        //if (entity))
        if (entity instanceof Creature creature) {
            creature.setCoordinates(coordinates);
        }
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        BoardUtils.validateCoordinates(this, coordinates);
        Entity result = entities.get(coordinates);
        if (result == null) {
            throw new NoSuchElementException("There is no entity on game board at " + coordinates);
        }
        return result;
    }

    public void removeEntity(Coordinates coordinates) {
        BoardUtils.validateCoordinates(this, coordinates);
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        BoardUtils.validateCoordinates(this, from);
        BoardUtils.validateCoordinates(this, to);
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    public <T extends Entity> List<T> getEntitiesBy(Class<T> entityType) {
        if (entityType == null) {
            throw new IllegalArgumentException("The entityType can not be null!");
        }
        List<T> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (entityType.isInstance(entity)) {
                result.add(entityType.cast(entity));
            }
        }
        return result;
    }

    public void clearEntities() {
        entities.clear();
    }

    public boolean isExists(Entity entity) {
        return entities.containsValue(entity);
    }

    public boolean isCoordinatesEmpty(Coordinates coordinates) {
        BoardUtils.validateCoordinates(this, coordinates);
        return !entities.containsKey(coordinates);
    }
}
