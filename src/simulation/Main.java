package simulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameBoard gameBoard = new GameBoard(10,10 );
        gameBoard.setupGrassPositions();
        gameBoard.setupDeerPositions();
        gameBoard.setupRockPositions();
        gameBoard.setupWolfPositions();
        gameBoard.setupTreePositions();
        Simulation simulation = new Simulation(gameBoard);
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