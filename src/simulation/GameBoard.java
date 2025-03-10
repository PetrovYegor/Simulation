package simulation;

import simulation.models.Coordinate;
import simulation.models.Creature;
import simulation.models.Herbivore;

import java.util.*;

public class GameBoard {
    private final int height;
    private final int width;
    private List<Herbivore> herbivores;
    //сделать мапу, где ключ - существо, а значение - его расположение (так по ТЗ написано)
    Map<Coordinate, Herbivore> gameBoard;
    private String[][] board;

    private TreeMap<Coordinate, Herbivore> initGameBoard(int height, int width){
        TreeMap<Coordinate, Herbivore> result = new TreeMap<>();
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                result.put(new Coordinate(i, j), null);
            }
        }
        return result;
    }

    public GameBoard(int height, int width, List<Herbivore> herbivores){
        this.height = height;
        this.width = width;
        this.herbivores = herbivores;
        this.gameBoard = initGameBoard(height, width);
        this.board = new String[height][width];
    }

    public String[][] getBoard(){
        return board;
    }

    public List<Herbivore> getHerbivores(){
        List<Herbivore> result = new ArrayList<>(herbivores);
        return result;
    }

    public void removeCreature(Creature cr){
        herbivores.remove(cr);
    }

    public void addCreature(Herbivore cr){
        herbivores.add(cr);
    }

    public void setUpHerbivoresOnBoard(){
        for (Herbivore herbivore : herbivores){
            int x = herbivore.getCoordinate().getX();
            int y = herbivore.getCoordinate().getY();
            board[x][y] = herbivore.getSprite();
        }
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public boolean isCellTaken(Coordinate c){
        return !"_".equals(board[c.getX()][c.getY()]);
    }
}

//заполнить размер поля через диалог?
//Карта, содержит в себе коллекцию для хранения существ и их расположения.
// Советую не спешить использовать двумерный массив или список списков,
// а подумать какие ещё коллекции могут подойти.