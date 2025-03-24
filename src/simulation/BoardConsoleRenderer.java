package simulation;

import simulation.models.*;

public class BoardConsoleRenderer {//существительное и что будет делать
    private final GameBoard board;

    BoardConsoleRenderer(GameBoard board) {
        this.board = board;
    }

    public void render() {//цикл выводить будем построчно. Для пустой клетки - пустота. Для занятой - спрайт фигуры
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (Creature cr : board.getCreatures()){
            System.out.println(cr);
        }

        for (int i = 0; i < board.getHeight(); i++) {
            String line = "";
            for (int j = 0; j < board.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (board.isCellEmpty(coordinates)) {
                    line += getSpriteForEmptyCell();
                } else {
                    Object entity = board.getEntity(coordinates);
                    line += getEntitySprite(entity);
                }


            }
            System.out.println(line);
        }
    }//принимает доску и рендерим

    private String getSpriteForEmptyCell() {

        return Sprite.GROUND;
    }

    private String getEntitySprite(Object entity) {//поменять аргумент
        String resultSptite = "";
        if (entity instanceof Herbivore){
            resultSptite = Sprite.HERBIVORE;
        } else if (entity instanceof Grass){
            resultSptite = Sprite.GRASS;
        } else if (entity instanceof Rock){
            resultSptite = Sprite.ROCK;
        } else if (entity instanceof Predator){
            resultSptite = Sprite.PREDATOR;
        } else if (entity instanceof Tree){
            resultSptite = Sprite.TREE;
        }
        return resultSptite;
    }
    //+ константы для раскрашивания фона символа в консоли
    //+ методы раскраски


}
//мб это не должно быть в actions
//совершает операции над картой GameBoard (принимает при создании)
/*
Рендерер #
Рендерер ответственен за визуализацию состояния поля, его отрисовку.
По желанию студента интерфейс приложения может быть консольным, либо графическим.
* */