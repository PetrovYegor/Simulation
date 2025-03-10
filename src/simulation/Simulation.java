package simulation;

import simulation.actions.Action;
import simulation.models.Coordinate;
import simulation.models.Herbivore;

import java.util.List;

public class Simulation {
    private GameBoard gameBoard;//игровое поле
    private int moveCounter;//счётчик ходов

    private GameRenderer gameRenderer = new GameRenderer();
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
        gameRenderer.printGameBoard(gameBoard, gameBoard.getHerbivores());
    }

    void startSimulation() throws InterruptedException {
        List<Herbivore> herbivores = gameBoard.getHerbivores();
        while (true){
            //gameBoard.setUpHerbivoresOnBoard();
            nextTurn();
            for (Herbivore herbivore : herbivores){
                boolean needToCalculateNewCoordinate = true;
                while (needToCalculateNewCoordinate){
                    Coordinate currentCoordinate = herbivore.getCoordinate();
                    herbivore.makeMove();
                    Coordinate newCoordinate = herbivore.getCoordinate();
                    if (gameBoard.isCellTaken(newCoordinate)){
                        herbivore.setCoordinate(currentCoordinate);
                    } else {
                        gameBoard.getBoard()[currentCoordinate.getX()][currentCoordinate.getY()] = "_";
                        gameBoard.getBoard()[newCoordinate.getX()][newCoordinate.getY()] = herbivore.getSprite();
                        needToCalculateNewCoordinate = false;
                    }
                }


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