package simulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameBoard gameBoard = new GameBoard(4,4 );
        gameBoard.setupGrassPositions();
        gameBoard.setupHerbivoresPositions();
        //gameBoard.setupRockPositions();
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