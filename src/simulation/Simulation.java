package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddDeerAction;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.MakeMoveAction;
import simulation.models.Creature;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final GameBoard board;
    private int moveCounter = 0;
    private final BoardConsoleRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;

    public Simulation(GameBoard board) {
        this.board = board;
        this.renderer = new BoardConsoleRenderer(board);
        initActions = getInitActions();
        this.turnActions = getTurnActions();
    }

    private List<Action> getInitActions() {
        List<Action> result = new ArrayList<>();
        result.add(new SetupGrassAction(board));
        result.add(new SetupRockAction(board));
        result.add(new SetupWolfAction(board));
        result.add(new SetupTreeAction(board));
        result.add(new SetupDeerAction(board));
        return result;
    }

    public List<Action> getTurnActions() {
        List<Action> result = new ArrayList<>();
        result.add(new AddDeerAction(board));
        result.add(new AddGrassAction(board));
        result.add(new MakeMoveAction(board));
        return result;
    }

    private void nextTurn() {
        renderer.render();
        for (Action action : turnActions){
            action.execute();
        }
        moveCounter++;
    }

    void startSimulation() throws InterruptedException {
        for (Action action : initActions) {
            action.execute();
        }

        while (true) {
            nextTurn();
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