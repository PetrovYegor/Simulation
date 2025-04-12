package simulation;

import simulation.models.*;

import java.util.NoSuchElementException;

public class GameBoardRenderer {
    private static final String GRASS = "\uD83C\uDF3F";
    private static final String TREE = "\uD83C\uDF33";
    private static final String ROCK = "⛰\uFE0F";
    private static final String HERBIVORE = "\uD83E\uDD8C";
    private static final String PREDATOR = "\uD83D\uDC3A";
    private static final String GROUND = "\uD83D\uDFEB";
    private final GameBoard board;

    GameBoardRenderer(GameBoard board) {
        this.board = board;
    }

    public void render() {
        System.out.println();

        for (int i = 0; i < board.getHeight(); i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < board.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (board.isCoordinatesEmpty(coordinates)) {
                    line.append(getSpriteForEmptyCell());
                } else {
                    Entity entity = board.getEntity(coordinates);
                    line.append(getEntitySprite(entity));
                }
            }
            System.out.println(line);
        }
    }

    private String getSpriteForEmptyCell() {
        String resultSprite = GROUND;
        if (resultSprite == null) {
            throw new NoSuchElementException("Sprite for empty cell is null");
        }
        return resultSprite;
    }

    private String getEntitySprite(Entity entity) {
        if (entity instanceof Herbivore) {
            return HERBIVORE;
        }
        if (entity instanceof Grass) {
            return GRASS;
        }
        if (entity instanceof Rock) {
            return ROCK;
        }
        if (entity instanceof Predator) {
            return PREDATOR;
        }
        if (entity instanceof Tree) {
            return TREE;
        }
        throw new IllegalArgumentException("The entity is null or there is no sprite for current entity");
    }
}