package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.List;

public abstract class Creature extends Entity {
    public final int speed;
    public int health;
    private static final int ATTACK_DISTANCE = 1;

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public void makeMove(List<Coordinates> coordinatesForMoving, GameBoard board) {
        if (canAttack(coordinatesForMoving)) {
            Coordinates target = coordinatesForMoving.get(0);
            attack(target, board);
        } else {
            moveToFood(coordinatesForMoving, board);
        }
    }

    public boolean canAttack(List<Coordinates> c) {
        return c.size() == ATTACK_DISTANCE;
    }

    public int getSpeed() {
        return speed;
    }

//    public static int getRandomHealth() {
//        return new Random().nextInt(4) + 3;//вынести в константу
//    }
//
//    public static int getRandomSpeed() {
//        return new Random().nextInt(5) + 1;//вынести в константу
//    }
//
//    public static int getRandomAttackPower() {
//        return new Random().nextInt(2) + 1;//вынести в константу
//    }

    public int getHealth() {
        return health;
    }

    public abstract void attack(Coordinates target, GameBoard board);

    public void eat(Coordinates coordinates, GameBoard board) {
        board.removeEntity(coordinates);
    }

    public void setHealth(int health) {
        this.health = health;
    }

    //получить уровень здоровья
    //проверить не равен ли уровень здоровья 0, если да, то мёртв
    //уменьшить количество здоровья
    //возможно нужен какой-то абстрактный метод движения к цели, травоядное к траве, хищник к травоядному

    public void moveToFood(List<Coordinates> coordinatesForMoving, GameBoard board) {
        int steps = 0;
        for (Coordinates newCoordinates : coordinatesForMoving) {
            if (steps < coordinatesForMoving.size() && steps < getSpeed()) {//пока количество сделанных шагов не превышает путь до цели и не кончились очки скорости
                if (steps == coordinatesForMoving.size() - 1) {//если creature находимся на расстоянии одной клетки от еды
                    return;
                }
                Coordinates oldCoordinates = getCoordinates();
                board.moveEntity(oldCoordinates, newCoordinates);
                steps++;
            }
        }
    }
}

