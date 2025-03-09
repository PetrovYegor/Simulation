package simulation.models;
//Травоядное, наследуется от Creature. Стремятся найти ресурс (траву),
// может потратить свой ход на движение в сторону травы, либо на её поглощение.

import simulation.Sprite;

import java.util.Random;

public class Herbivore extends Creature{
    private Coordinate coordinate;

    public Herbivore (int speed, Coordinate coordinates){
        super(speed);
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
        int resultX = -1;
        int resultY = -1;
        int stepCounter = 0;
        while (stepCounter < speed){
            int tempX = -1;
            int tempY = -1;
            int printX = -1;
            int printY = -1;
            if (stepCounter == 0){
                tempX = currentX;
                tempY = currentY;
            } else {
                tempX = resultX;
                tempY = resultY;
            }

            boolean changeRow = random.nextBoolean();
            boolean movePositive = random.nextBoolean();


            if (changeRow){
                if (movePositive){
                    tempX += 1;
                } else {
                    tempX -= 1;
                }
            } else {
                if (movePositive){
                    tempY += 1;
                } else {
                    tempY -= 1;
                }
            }

//            if (stepCounter == 0 && tempX == currentX && tempY == currentY){
//                continue;
//            }
            if (stepCounter != 0){
                if (tempX == resultX && tempY == resultY){
                    continue;
                }
            }


            if (tempX >= 0 && tempX < 10 && tempY >= 0 && tempY < 10){
                if (stepCounter == 0){
                    printX = tempX;
                    printY = tempY;
                } else {
                    printX = tempX;
                    printY = tempY;
                }
                if (stepCounter == 0){
                    System.out.println("("+currentX+"; "+currentY+") -----------> ("+tempX + "; "+tempY+")");
                } else {
                    System.out.println("("+resultX+"; "+resultY+") -----------> ("+printX + "; "+printY+")");
                }

                resultX = tempX;
                resultY = tempY;

                stepCounter += 1;
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
