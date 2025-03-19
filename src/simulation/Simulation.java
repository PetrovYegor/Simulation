package simulation;

import simulation.actions.Action;
import simulation.models.Creature;
import simulation.models.Entity;

import java.util.List;

public class Simulation {
    private GameBoard gameBoard;//игровое поле
    private int moveCounter;//счётчик ходов

    private BoardConsoleRenderer renderer;
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

    public Simulation(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.renderer = new BoardConsoleRenderer(gameBoard);//убрать эту зависимость
        moveCounter = 0;
    }

    private void nextTurn() {
        renderer.render();
    }

    void startSimulation() throws InterruptedException {
        while (true) {
            nextTurn();
//            if (!gameBoard.isGrassEnough()){//после окончания дебага раскомментировать
//                gameBoard.setupGrassPositions();
//            }
            for (Creature creature : gameBoard.getCreatures()) {
                creature.makeMove(gameBoard);
            }
            Thread.sleep(300);
        }
    }


    private void pauseSimulation() {

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