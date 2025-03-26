package simulation;

import simulation.actions.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameBoard gameBoard = new GameBoard(10,10 );
        Simulation simulation = new Simulation(gameBoard);
        List<Action> initActions = new ArrayList<>();
        initActions.add(new SetupGrassAction(gameBoard));
        initActions.add(new SetupRockAction(gameBoard));
        initActions.add(new SetupWolfAction(gameBoard));
        initActions.add(new SetupTreeAction(gameBoard));
        initActions.add(new SetupDeerAction(gameBoard));
        simulation.setInitActions(initActions);

        simulation.startSimulation();
    }
}
/*
Конечная цель #
Реализовать симуляцию и подобрать различные значения так, чтобы взаимодействия внутри мира
получились максимально интересными:

Размер поля
Диапазоны HP и скорости существ
Диапазон атаки хищников
Опциональные идеи для усложнения проекта:

Механика размножения существ
Механика голода, когда от отсутствия пищи у них начинает уменьшаться HP
* */