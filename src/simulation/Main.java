package simulation;

import simulation.actions.Action;
import simulation.actions.setup_actions.*;
import simulation.actions.turn_actions.AddGrassAction;
import simulation.actions.turn_actions.AddHerbivoreAction;
import simulation.actions.turn_actions.MakeMoveAction;

import java.util.ArrayList;
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

        printStartMenu();
        Scanner s = new Scanner(System.in);
        while (true){
            String result = s.nextLine();
            if (result.equalsIgnoreCase("start")){
                simulation.startSimulation();
            } else if (result.equalsIgnoreCase("stop")){
                simulation.pauseSimulation();
            } else if (result.equalsIgnoreCase("resume")){
                simulation.resumeSimulation();
            }
        }
    }

    private static void printStartMenu(){
        System.out.println("Enter start to start simulation");
    }
}