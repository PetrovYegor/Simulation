package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int GAMEBOARD_HEIGHT = 7;
    private static final int GAMEBOARD_WIDTH = 10;
    public static void main(String[] args) throws InterruptedException {
        GameBoard board = new GameBoard(GAMEBOARD_HEIGHT, GAMEBOARD_WIDTH);
        List<Action> initActions = List.of(
                new SetupPredatorAction(board),
                new SetupHerbivoreAction(board),
                new SetupGrassAction(board),
                new SetupRockAction(board),
                new SetupTreeAction(board)
                );
        List<Action> turnActions = List.of(
                new AddHerbivoreAction(board),
                new AddGrassAction(board),
                new MakeMoveAction(board)
        );

        Simulation simulation = new Simulation(board, initActions, turnActions);

        System.out.println("Simulation commands:");
        System.out.println("start  - begin simulation");
        System.out.println("pause  - pause simulation");
        System.out.println("resume - resume paused simulation");
        System.out.println("stop   - stop simulation completely");
        System.out.println("exit   - quit program");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "start":
                    simulation.startSimulation();
                    break;
                case "pause":
                    simulation.pauseSimulation();
                    break;
                case "resume":
                    simulation.resumeSimulation();
                    break;
                case "stop":
                    simulation.stopSimulation();
                    break;
                case "exit":
                    System.out.println("Exiting program...");
                    simulation.stopSimulation();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }

}