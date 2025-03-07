package simulation;

import simulation.models.Coordinate;
import simulation.models.Herbivore;

import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Coordinate coordinate = new Coordinate(5,5);
        Herbivore herbivore = new Herbivore(coordinate);
        List<Herbivore> herbivores = List.of(herbivore);
        GameBoard gameBoard = new GameBoard(10, 10, herbivores);
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