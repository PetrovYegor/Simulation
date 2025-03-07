package simulation;

import java.util.Random;

public class MainTest {
    public static void main(String[] args) {
        Random random = new Random();

        String[][] board = new String[10][10];
        for (int i = 0; i < board.length; i++){

            for (int j = 0; j < board[i].length; j++){
                int entity = random.nextInt(30);
                if (i == 5 && j == 5){
                    board[i][j] = Sprite.HERBIVORE;
                } else {
                    board[i][j] = ".";
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
