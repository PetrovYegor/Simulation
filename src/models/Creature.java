package models;

public abstract class Creature extends Entity{
    private int speed; //количество клеток за 1 ход
    private int health; //здоровье существа

    protected abstract void makeMove();
}
