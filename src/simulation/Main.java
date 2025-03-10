package simulation;

import simulation.models.Coordinate;
import simulation.models.Herbivore;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Herbivore> herbivores = new ArrayList<>();
        herbivores.add(HerbivoreFactory.createHerbivore(3, 5, new Coordinate(0,0)));
        herbivores.add(HerbivoreFactory.createHerbivore(5, 3, new Coordinate(1,1)));

        GameBoard gameBoard = new GameBoard(5,5 , herbivores);
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