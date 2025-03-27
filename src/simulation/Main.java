package simulation;

import simulation.actions.*;
import simulation.actions.setup_actions.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameBoard board = new GameBoard(10,10 );
        Simulation simulation = new Simulation(board);

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