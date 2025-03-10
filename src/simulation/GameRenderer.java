package simulation;

import simulation.models.Herbivore;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer {
    public void printGameBoard(GameBoard board, List<Herbivore> herbivores){
        int boardHeight = board.getHeight();
        int boardWidth = board.getWidth();
        List<Herbivore> hvs = new ArrayList<>(herbivores);
        for (int i = 0; i < boardHeight; i++){
            for (int j = 0; j < boardWidth; j++){
                String sprite = "_";
                for (Herbivore herbivore : hvs){
                    if (herbivore.isContainsCoordinates(i, j)){
                        sprite = herbivore.getSprite();
                        break;
                    }
                }
                board.getBoard()[i][j] = sprite;
                System.out.print(board.getBoard()[i][j]);
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