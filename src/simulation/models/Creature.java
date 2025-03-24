package simulation.models;

import simulation.Coordinates;
import simulation.GameBoard;

import java.util.Random;

//Абстрактный класс, наследуется от Entity. Существо, имеет скорость (сколько клеток может пройти за 1 ход)
// , количество HP. Имеет метод makeMove() - сделать ход.
public abstract class Creature extends Entity {
    public final int speed; //количество клеток, которое существо может пройти за 1 ход
    public int health; //здоровье существа

    public Creature(Coordinates coordinates, int speed, int health) {
        super(coordinates);
        this.speed = speed;
        this.health = health;
    }

    public abstract void makeMove();

    public int getSpeed() {
        return speed;
    }

    public static int getRandomHealth() {
        return new Random().nextInt(4) + 3;//вынести в константу
    }

    public static int getRandomSpeed() {
        return new Random().nextInt(5) + 1;//вынести в константу
    }

    public static int getRandomAttackPower(){
        return new Random().nextInt(2) + 1;//вынести в константу
    }

    public int getHealth() {
        return health;
    }

    public abstract void makeMove(GameBoard board);

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "speed=" + speed +
                ", health=" + health +
                '}';
    }//можно удалить, создал для дебага

    //получить уровень здоровья
    //проверить не равен ли уровень здоровья 0, если да, то мёртв
    //уменьшить количество здоровья
    //возможно нужен какой-то абстрактный метод движения к цели, травоядное к траве, хищник к травоядному
}

