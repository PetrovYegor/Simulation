package simulation;

import simulation.models.Herbivore;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer {
    public void printGameBoard(int height, int width, List<Herbivore> herbivores){
        int boardHeight = height;
        int boardWidth = width;
        List<Herbivore> hvs = new ArrayList<>(herbivores);
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                for (Herbivore herbivore : hvs){
                    if (herbivore.isContainsCoordinates(i, j)){
                        System.out.print(herbivore.getSprite());
                    } else {
                        System.out.print("_");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}
//мб это не должно быть в actions
//совершает операции над картой GameBoard (принимает при создании)
/*
Рендерер #
Рендерер ответственен за визуализацию состояния поля, его отрисовку.
По желанию студента интерфейс приложения может быть консольным, либо графическим.
* */