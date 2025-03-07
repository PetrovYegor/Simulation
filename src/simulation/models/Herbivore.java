package simulation.models;
//Травоядное, наследуется от Creature. Стремятся найти ресурс (траву),
// может потратить свой ход на движение в сторону травы, либо на её поглощение.

import simulation.Sprite;

import java.util.Random;

public class Herbivore extends Creature{
    private Coordinate coordinate;

    public Herbivore (Coordinate coordinates){
        this.coordinate = coordinates;
    }
    public Coordinate getCoordinate(){
        return coordinate;
    }
    @Override
    public void makeMove() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        changeCoordinate(x, y);
    }

    private void changeCoordinate(int x, int y){
        coordinate = new Coordinate(x, y);
    }

    public String getSprite(){
        return Sprite.HERBIVORE;
    }

    //нужен метод движения
    //съесть траву
}
