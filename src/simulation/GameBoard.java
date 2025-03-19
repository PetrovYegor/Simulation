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

    //установить фигуру на игровой карте
    public void setEntity(Coordinates coordinates, Entity entity) {
        entity.coordinates = coordinates;
        entities.put(coordinates, entity);
    }

    public void setupHerbivoresPositions() {
        //for (int i = 0; i < EntityLimitSettings.HERBIVORE_LIMIT; i++) {
        for (int i = 0; i < 2; i++) {
            Coordinates randomFreeCoordinates = getFreeCoordinates();
            int x = randomFreeCoordinates.getX();
            int y = randomFreeCoordinates.getY();
            setEntity(new Coordinates(2, 8), new Herbivore(new Coordinates(2, 8), 2, 2));
            setEntity(new Coordinates(3, 6), new Herbivore(new Coordinates(3, 6), 2, 2));
            //setEntity(new Coordinates(x, y), new Herbivore(new Coordinates(x, y), Creature.getRandomSpeed(), Creature.getRandomHealth()));
        }
    }


    public void setupGrassPositions() {
        //for (int i = 0; i < EntityLimitSettings.GRASS_LIMIT; i++) {
        for (int i = 0; i < 2; i++) {
            Coordinates randomFreeCoordinates = getFreeCoordinates();
            int x = randomFreeCoordinates.getX();
            int y = randomFreeCoordinates.getY();
            //setEntity(new Coordinates(x, y), new Grass(new Coordinates(x, y)));
            setEntity(new Coordinates(1, 7), new Grass(new Coordinates(1, 7)));
            setEntity(new Coordinates(0, 7), new Grass(new Coordinates(0, 7)));
        }
    }

    public void setupRockPositions() {
        for (int i = 0; i < EntityLimitSettings.ROCK_LIMIT; i++) {
            Coordinates randomFreeCoordinates = getFreeCoordinates();
            int x = randomFreeCoordinates.getX();
            int y = randomFreeCoordinates.getY();
            setEntity(new Coordinates(x, y), new Rock(new Coordinates(x, y)));
        }
    }

    //Нужно создать метод, который будет вовзращать игровую мапу по переданной ширине и высоте, при условии, что ширина и высота валидная. Спросить у ИИ как сделать, какой-то паттерн, наверное
    private Coordinates getFreeCoordinates() {
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
        return getGrass().size() != 0 ? true : false;
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

    public boolean isBarrier(Coordinates coordinates) {
        Entity targetEntity = entities.get(coordinates);
        if (isRock(targetEntity) || isTree(targetEntity)) {
            return true;
        }
        return false;
    }

    public boolean isCellEmpty(Coordinates coordinates) {//метод для рендера
        return !entities.containsKey(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {//метод для рендера
        return entities.get(coordinates);
    }

    public void removeEntity(Coordinates coordinates) {
        entities.remove(coordinates);
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

    public boolean isCreature(Entity entity) {
        return entity instanceof Creature;
    }

    public boolean isGrass(Entity entity) {
        return entity instanceof Grass;
    }

    public boolean isRock(Entity entity) {
        return entity instanceof Rock;
    }

    public boolean isTree(Entity entity) {
        return entity instanceof Tree;
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
// Советую не спешить использовать двумерный массив или список списков,
// а подумать какие ещё коллекции могут подойти.