import actions.Action;

import java.util.List;

public class Simulation {
    private Map gameMap;
    private int moveCounter;

    //действия, совершаемые перед стартом симуляции. Пример - расставить объекты
    //и существ на карте
    List<Action> initActions;
    //действия, совершаемые каждый ход. Примеры - передвижение существ, добавить травы
    //или травоядных, если их осталось слишком мало
    List<Action> turnActions;

    private void nextTurn(){

    }

    private void startSimulation(){

    }

    private void pauseSimulation(){

    }
}
/*
Главный класс приложения, включает в себя:

Карту
Счётчик ходов
Рендерер поля
Actions - список действий, исполняемых перед стартом симуляции или на каждом ходу (детали ниже)

Методы:

nextTurn() - просимулировать и отрендерить один ход
startSimulation() - запустить бесконечный цикл симуляции и рендеринга
pauseSimulation() - приостановить бесконечный цикл симуляции и рендеринга
* */