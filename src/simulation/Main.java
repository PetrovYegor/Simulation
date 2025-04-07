package simulation;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        GameBoard board = new GameBoard(7, 7);
        Simulation simulation = new Simulation(board);
        simulation.startSimulation();
    }
}