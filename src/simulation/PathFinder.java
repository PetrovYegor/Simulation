package simulation;

import simulation.models.*;

import java.util.*;

public class PathFinder {
    private final GameBoard board;
    List<Coordinates> visited;
    Map<Coordinates, Coordinates> cameFrom;
    Queue<Coordinates> bfsQueue;

    public PathFinder(GameBoard board) {
        this.board = board;
        visited = new ArrayList<>();
        cameFrom = new HashMap<>();
        bfsQueue = new LinkedList<>();
    }

    public List<Coordinates> searchFood(Coordinates start) {
        board.validateCoordinates(start, "searchFood");
        List<Coordinates> result = Collections.emptyList();
        Creature currentCreature = (Creature) board.getEntity(start);
        bfsQueue.add(start);

        while (!isBfsQueueEmpty()) {
            Coordinates current = bfsQueue.poll();
            if (isVisited(current)) {
                continue;
            }
            markAsVisited(current);
            if (isBeginningOfSearch(current, start)) {
                addPredecessors(current);
                continue;
            }
            if (!board.isCoordinatesEmpty(current)) {
                if (board.isFood(current, currentCreature)) {
                    result = reconstructPath(current);
                    break;
                }
                continue;
            }
            addPredecessors(current);
        }
        return result;
    }

    private void addPredecessors(Coordinates current) {
        board.validateCoordinates(current, "addPredecessors");
        Coordinates[] directions = getShiftDirections();
        for (Coordinates coordinates : directions) {
            int newX = current.x() + coordinates.x();
            int newY = current.y() + coordinates.y();
            Coordinates newCoordinates = new Coordinates(newX, newY);
            if (board.isCoordinatesValid(newCoordinates)) {
                if (!isVisited(newCoordinates) && !bfsQueue.contains(newCoordinates)) {
                    bfsQueue.add(newCoordinates);
                    cameFrom.put(newCoordinates, current);
                }
            }
        }
    }

    private Coordinates[] getShiftDirections() {
        return new Coordinates[]{
                new Coordinates(-1, 0),
                new Coordinates(1, 0),
                new Coordinates(0, -1),
                new Coordinates(0, 1)
        };
    }

    private LinkedList<Coordinates> reconstructPath(Coordinates target) {
        board.validateCoordinates(target, "reconstructPath");
        LinkedList<Coordinates> wayToTarget = new LinkedList<>();
        Coordinates current = target;
        do {
            wayToTarget.addFirst(current);
            current = cameFrom.get(current);
        } while (current != null);
        wayToTarget.removeFirst();
        return wayToTarget;
    }

    private boolean isVisited(Coordinates target) {
        board.validateCoordinates(target, "isVisited");
        return visited.contains(target);
    }

    private void markAsVisited(Coordinates c) {
        board.validateCoordinates(c, "markAsVisited");
        visited.add(c);
    }

    private boolean isBeginningOfSearch(Coordinates current, Coordinates start) {
        board.validateCoordinates(current, "isBeginningOfSearch (current)");
        board.validateCoordinates(start, "isBeginningOfSearch (start)");
        return current.equals(start);
    }

    private boolean isBfsQueueEmpty() {
        return bfsQueue.isEmpty();
    }

    public static class GameBoardRenderer {
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
            } else if (entity instanceof Grass) {
                resultSptite = Sprite.GRASS;
            } else if (entity instanceof Rock) {
                resultSptite = Sprite.ROCK;
            } else if (entity instanceof Predator) {
                resultSptite = Sprite.PREDATOR;
            } else if (entity instanceof Tree) {
                resultSptite = Sprite.TREE;
            }
            if (resultSptite == null) {
                throw new IllegalArgumentException("The entity is null or there is no sprite for current entity");
            }
            return resultSptite;
        }
    }
}
