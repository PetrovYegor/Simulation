package simulation;

import simulation.models.Creature;
import simulation.models.Herbivore;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int height;
    private final int width;
    private List<Herbivore> herbivores;
    //сделать мапу, где ключ - существо, а значение - его расположение (так по ТЗ написано)
    private String[][] board;

    public GameBoard(int height, int width, List<Herbivore> herbivores){
        this.height = height;
        this.width = width;
        this.herbivores = herbivores;
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
}

//заполнить размер поля через диалог?
//Карта, содержит в себе коллекцию для хранения существ и их расположения.
// Советую не спешить использовать двумерный массив или список списков,
// а подумать какие ещё коллекции могут подойти.