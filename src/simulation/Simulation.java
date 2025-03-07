package simulation;

import simulation.actions.Action;
import simulation.models.Herbivore;

import java.util.List;

public class Simulation {
    private GameBoard gameBoard;//игровое поле
    private int moveCounter;//счётчик ходов

    //private GameRenderer renderer //рендерер игры (по ТЗ)

    //private Action[] initActions;// действия, совершаемые перед стартом симуляции. Пример - расставить объекты
    // и существ на карте

    //private Action[] turnActions - действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы
    //или травоядных, если их осталось слишком мало

    //действия, совершаемые перед стартом симуляции. Пример - расставить объекты
    //и существ на карте
    List<Action> initActions;
    //действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы
    //или травоядных, если их осталось слишком мало
    List<Action> turnActions;

    public Simulation (GameBoard board){
        this.gameBoard = board;
        moveCounter = 0;
    }

    private void nextTurn(){
        String[][] board = gameBoard.getBoard();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    void startSimulation() throws InterruptedException {
        gameBoard.setUpHerbivoresOnBoard();
        List<Herbivore> herbivores = gameBoard.getHerbivores();
        while (true){
            nextTurn();
            for (Herbivore herbivore : herbivores){
                herbivore.makeMove();
            }
            Thread.sleep(500);
        }

    }

    private void pauseSimulation(){

    }
}
/*
Главный класс приложения, включает в себя:

Карту
Счётчик ходов
Рендерер поля
Actions - методы (список действий), исполняемые перед стартом симуляции или на каждом ходу (детали ниже)

Методы:

nextTurn() - просимулировать и отрендерить один ход//каждый ход
startSimulation() - запустить бесконечный цикл симуляции и рендеринга//выполняется при старте симуляции
pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга//выполняется при стопе симуляции
* */