package simulation;

import simulation.actions.Action;
import simulation.models.Creature;
import simulation.models.Entity;

import java.util.List;

public class Simulation {
    private GameBoard gameBoard;//игровое поле
    private int moveCounter;//счётчик ходов

    private BoardConsoleRenderer renderer;

    //действия, совершаемые перед стартом симуляции. Пример - расставить объекты
    //и существ на карте
    //public List<Action> initActions;
    //действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы
    //или травоядных, если их осталось слишком мало

    public List<Action> initActions;
    List<Action> turnActions;

    public Simulation(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.renderer = new BoardConsoleRenderer(gameBoard);//убрать эту зависимость
        moveCounter = 0;
    }

    public void setInitActions(List<Action> actions){
        initActions = actions;
    }

    public void setTurnActions(List<Action> actions){
        turnActions = actions;
    }



    private void nextTurn() {
        renderer.render();
    }

    void startSimulation() throws InterruptedException {
        for (Action action : initActions){
            action.execute();
        }

        while (true) {
//            if (!gameBoard.isGrassEnough()){//после окончания дебага раскомментировать
//                gameBoard.setupGrassPositions();
//            }
//            if (!gameBoard.isHerbivoreEnough()){//после окончания дебага раскомментировать
//                gameBoard.setupDeerPositions();
//            }
            nextTurn();
            for (Creature creature : gameBoard.getCreatures()) {
                creature.makeMove(gameBoard);
            }
            Thread.sleep(1000);
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

/*
Action - действие, совершаемое над миром. Например - сходить всеми существами.
Это действие итерировало бы существ и вызывало каждому makeMove().
Каждое действие описывается отдельным классом и совершает операции над картой.
Симуляция содержит 2 массива действий:
initActions - действия, совершаемые перед стартом симуляции. Пример - расставить объекты
 и существ на карте

turnActions - действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы
или травоядных, если их осталось слишком мало
* */