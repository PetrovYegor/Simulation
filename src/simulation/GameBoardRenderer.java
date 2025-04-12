package simulation;

import simulation.models.*;

import java.util.NoSuchElementException;

public class GameBoardRenderer {
    private final GameBoard board;

    GameBoardRenderer(GameBoard board) {
        this.board = board;
    }

    public void render() {
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
        String resultSprite = Sprite.GROUND;
        if (resultSprite == null) {
            throw new NoSuchElementException("Sprite for empty cell is null");
        }
        return resultSprite;
    }

    private String getEntitySprite(Entity entity) {
        String resultSptite = null;
        if (entity instanceof Herbivore) {
            resultSptite = Sprite.HERBIVORE;
        }
        if (entity instanceof Grass) {
            resultSptite = Sprite.GRASS;
        }
        if (entity instanceof Rock) {
            resultSptite = Sprite.ROCK;
        }
        if (entity instanceof Predator) {
            resultSptite = Sprite.PREDATOR;
        }
        if (entity instanceof Tree) {
            resultSptite = Sprite.TREE;
        }
        if (resultSptite == null) {
            throw new IllegalArgumentException("The entity is null or there is no sprite for current entity");
        }
        return resultSptite;
    }

}