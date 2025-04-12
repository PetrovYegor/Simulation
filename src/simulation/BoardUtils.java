package simulation;

import simulation.actions.ActionUtils;
import simulation.exceptions.InvalidCoordinateException;
import simulation.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BoardUtils {
    private BoardUtils() {
    }

    public static Coordinates getRandomFreeCoordinates(GameBoard board, int height, int width) {
        List<Coordinates> allCoordinates = getAllGameBoardCoordinates(height, width);
        Collections.shuffle(allCoordinates);
        for (Coordinates c : allCoordinates) {
            if (board.isCoordinatesEmpty(c)) {
                return c;
            }
        }
        throw new IllegalStateException("The game board cannot be without free cells!");
    }

    public static List<Coordinates> getAllGameBoardCoordinates(int height, int width) {
        List<Coordinates> result = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result.add(new Coordinates(i, j));
            }
        }
        return result;
    }

    public static boolean isCoordinatesValid(Coordinates coordinates, int height, int width) {
        int targetX = coordinates.x();
        int targetY = coordinates.y();
        boolean xValid = targetX >= 0 && targetX < height;
        boolean yValid = targetY >= 0 && targetY < width;
        return xValid && yValid;
    }

    public static void validateCoordinates(GameBoard board, Coordinates c) {
        if (!isCoordinatesValid(c, board.getHeight(), board.getWidth())) {
            throw new InvalidCoordinateException(
                    String.format(
                            "Invalid coordinates (%d, %d). Field size: %dx%d",
                            c.x(),
                            c.y(),
                            board.getHeight(),
                            board.getWidth()
                    )
            );
        }
    }

    public static boolean isFood(GameBoard board, Coordinates targetCoordinates, Creature creature) {
        BoardUtils.validateCoordinates(board, targetCoordinates);
        if (!board.isExists(creature)) {
            throw new IllegalArgumentException("The creature is null. Creature can not be null");
        }
        Entity targetEntity = board.getEntity(targetCoordinates);
        if (targetEntity instanceof Grass && creature instanceof Herbivore) {
            return true;
        }
        if (targetEntity instanceof Herbivore && creature instanceof Predator) {
            return true;
        }
        return false;
    }
}
