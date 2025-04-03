package simulation;

import simulation.models.*;

public class BoardConsoleRenderer {
    private final GameBoard board;
    BoardConsoleRenderer(GameBoard board) {
        this.board = board;
    }
    public void render() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        for (int i = 0; i < board.getHeight(); i++) {
            String line = "";
            for (int j = 0; j < board.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (board.isCoordinatesEmpty(coordinates)) {
                    line += getSpriteForEmptyCell();
                } else {
                    Entity entity = board.getEntity(coordinates);
                    line += getEntitySprite(entity);
                }
            }
            System.out.println(line);
        }
    }

    private String getSpriteForEmptyCell() {
        return Sprite.GROUND;
    }

    private String getEntitySprite(Entity entity) {//поменять аргумент
        String resultSptite = "";
        if (entity instanceof Herbivore) {
            resultSptite = Sprite.HERBIVORE;
        } else if (entity instanceof Grass) {
            resultSptite = Sprite.GRASS;
        }
        else if (entity instanceof Rock) {
            resultSptite = Sprite.ROCK;
        }
//        else if (entity instanceof Predator) {
//            resultSptite = Sprite.PREDATOR;
//        } else if (entity instanceof Tree) {
//            resultSptite = Sprite.TREE;
//        }
        return resultSptite;
    }
}