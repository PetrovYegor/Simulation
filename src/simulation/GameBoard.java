package simulation;

import simulation.exceptions.InvalidCoordinateException;
import simulation.models.*;

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
        validateCoordinates(coordinates, "setEntity");
        entity.setCoordinates(coordinates);
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        validateCoordinates(coordinates, "getEntity");
        Entity result = entities.get(coordinates);
        if (result == null) {
            throw new NoSuchElementException("There is no entity on game board at " + coordinates);
        }
        return result;
    }

    public void removeEntity(Coordinates coordinates) {
        validateCoordinates(coordinates, "removeEntity");
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to) {
        validateCoordinates(from, "removeEntity (from)");
        validateCoordinates(to, "removeEntity (to)");
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
        List<Coordinates> allCoordinates = getAllGameBoardCoordinates();
        Collections.shuffle(allCoordinates);
        for (Coordinates c : allCoordinates) {
            if (isCoordinatesEmpty(c)) {
                return c;
            }
        }
        throw new IllegalStateException("The game board cannot be without free cells!");
    }

    private List<Coordinates> getAllGameBoardCoordinates() {
        List<Coordinates> result = new ArrayList<>();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                result.add(new Coordinates(i, j));
            }
        }
        return result;
    }

    public boolean isCoordinatesEmpty(Coordinates coordinates) {
        validateCoordinates(coordinates, "isCoordinatesEmpty");
        return !entities.containsKey(coordinates);
    }

    public boolean isCoordinatesValid(Coordinates coordinates) {//это лучше в класс координат перенести
        int targetX = coordinates.x();
        int targetY = coordinates.y();
        boolean xValid = targetX >= 0 && targetX < getHeight();
        boolean yValid = targetY >= 0 && targetY < getWidth();
        return xValid && yValid;
    }

    public void validateCoordinates(Coordinates c, String methodName) {
        if (!isCoordinatesValid(c)) {
            throw new InvalidCoordinateException(
                    String.format(
                            "[%s] Invalid coordinates (%d, %d). Field size: %dx%d",
                            methodName,
                            c.x(),
                            c.y(),
                            width,
                            height
                    )
            );
        }
    }

    public boolean isFood(Coordinates targetCoordinates, Creature creature) {
        validateCoordinates(targetCoordinates, "isCoordinatesEmpty");
        Entity targetEntity = entities.get(targetCoordinates);
        if (isGrass(targetEntity) && isHerbivore(creature)) {
            return true;
        }
        if (isHerbivore(targetEntity) && isPredator(creature)) {
            return true;
        }
        return false;
    }

    public boolean isGrass(Entity entity) {
        return entity instanceof Grass;
    }

    public boolean isHerbivore(Entity entity) {
        return entity instanceof Herbivore;
    }

    public boolean isPredator(Entity entity) {
        return entity instanceof Predator;
    }

    public boolean isGrassEnough() {
        return getCertainEntities(Grass.class).size() > EntityLimitSettings.HERBIVORE_LIMIT + 1 ? true : false;
    }

    public boolean isHerbivoreEnough() {
        return getCertainEntities(Herbivore.class).size() > EntityLimitSettings.PREDATOR_LIMIT ? true : false;
    }
}
