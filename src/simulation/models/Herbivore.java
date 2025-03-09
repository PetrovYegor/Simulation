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
//    @Override
//    public void makeMove() {
//        Random random = new Random();
//        int x = random.nextInt(10);
//        int y = random.nextInt(10);
//        changeCoordinate(x, y);
//    }

    @Override
    public void makeMove() {
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        changeCoordinate(this.getSpeed());
    }

//    private void changeCoordinate(int x, int y){
//        coordinate = new Coordinate(x, y);
//    }

    private void changeCoordinate(int speed){
        Random random = new Random();
        int currentX = coordinate.getX();
        int currentY = coordinate.getY();
        int resultX = 0;
        int resultY = 0;

        while (true){
            int tempX = currentX;
            int tempY = currentY;
            boolean changeRow = random.nextBoolean();
            boolean changeColumn = random.nextBoolean();
            boolean movePositiveX = random.nextBoolean();
            boolean movePositiveY = random.nextBoolean();


            if (changeRow){
                if (movePositiveX){
                    tempX += 1;
                } else {
                    tempX -= 1;
                }
            }

            if (changeColumn){
                if (movePositiveY){
                    tempY += 1;
                } else {
                    tempY -= 1;
                }
            }
            if (tempX == currentX && tempY == currentY){
                continue;
            }

            if (tempX >= 0 && tempX < 10 && tempY >= 0 && tempY < 10){
                resultX = tempX;
                resultY = tempY;
                break;
            }
        }
        System.out.println("("+currentX+"; "+currentY+") -----------> ("+resultX + "; "+resultY+")");
        coordinate = new Coordinate(resultX, resultY);
    }

    public boolean isContainsCoordinates(int x, int y){
        return (this.coordinate.getX() == x && this.coordinate.getY() == y);
    }

    public String getSprite(){
        return Sprite.HERBIVORE;
    }

    //нужен метод движения
    //съесть траву
}
