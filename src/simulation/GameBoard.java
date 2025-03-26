package simulation;

import simulation.models.*;

import java.util.*;

public class GameBoard {
    private final int height;
    private final int width;

    private final Random random;
    private final Map<Coordinates, Entity> entities;


    public GameBoard(int height, int width) {
        this.height = height;
        this.width = width;
        random = new Random();
        this.entities = new HashMap<>();
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public Entity getEntity(Coordinates coordinates) {
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
    }

    public void moveEntity(Coordinates from, Coordinates to){
        Entity entity = getEntity(from);
        removeEntity(from);
        setEntity(to, entity);
    }

    //Нужно создать метод, который будет вовзращать игровую мапу по переданной ширине и высоте, при условии, что ширина и высота валидная. Спросить у ИИ как сделать, какой-то паттерн, наверное
    public Coordinates getFreeCoordinates() {
        Set<Coordinates> takenCoordinates = entities.keySet();
        Set<Coordinates> allGameBoardCoordinates = getAllGameBoardCoordinates();
        if (takenCoordinates == null || isNullOrEmptySet(allGameBoardCoordinates)) {
            throw new IllegalArgumentException("The Set cannot be null.");
        }
        allGameBoardCoordinates.removeAll(takenCoordinates);
        int randomIndex = random.nextInt(allGameBoardCoordinates.size());
        int counter = 0;
        for (Coordinates c : allGameBoardCoordinates) {
            if (counter == randomIndex) {
                return c;
            }
            counter++;
        }
        throw new IllegalStateException("Something went wrong while picking a random element.");
    }

    private boolean isNullOrEmptySet(Set<Coordinates> coordinates) {
        return (coordinates == null || coordinates.isEmpty());
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

    public boolean isGrassEnough() {
        return getGrass().size() > EntityLimitSettings.DEER_LIMIT + 1 ? true : false;
    }

    public boolean isHerbivoreEnough() {
        return getHerbivores().size() >= EntityLimitSettings.PREDATOR_LIMIT ? true : false;
    }

    public boolean isFood(Coordinates targetCoordinates, Entity creature) {
        Entity targetEntity = entities.get(targetCoordinates);
        if (isGrass(targetEntity) && isHerbivore(creature)) {
            return true;
        }
        if (isHerbivore(targetEntity) && isPredator(creature)) {
            return true;
        }
        return false;
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entities.containsKey(coordinates);
    }

    public List<Creature> getCreatures() {
        List<Creature> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (isCreature(entity)) {
                result.add((Creature) entity);
            }
        }
        return result;
    }

    public List<Grass> getGrass() {
        List<Grass> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (isGrass(entity)) {
                result.add((Grass) entity);
            }
        }
        return result;
    }

    public List<Herbivore> getHerbivores() {
        List<Herbivore> result = new ArrayList<>();
        for (Entity entity : entities.values()) {
            if (isHerbivore(entity)) {
                result.add((Herbivore) entity);
            }
        }
        return result;
    }

    public boolean isCreature(Entity entity) {
        return entity instanceof Creature;
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

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static boolean isCoordinatesValid(Coordinates coordinates, int height, int width) {
        int targetX = coordinates.getX();
        int targetY = coordinates.getY();
        boolean xValid = targetX >= 0 && targetX < height;//!!!!!! убрать хардкод
        boolean yValid = targetY >= 0 && targetY < width;//!!!!!! убрать хардкод
        return xValid && yValid;
    }
}

//заполнить размер поля через диалог?
//Карта, содержит в себе коллекцию для хранения существ и их расположения.
