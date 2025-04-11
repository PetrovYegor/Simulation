package simulation;

import simulation.actions.Action;

import java.util.List;
import java.util.Scanner;

public class Simulation {
    private static final String ITERATION_COUNT = "The number of iterations of the game cycle: %d";
    private static final String SIMULATION_IS_RUNNING = "Simulation is already running! %s";
    private static final String REENTRY_START = "You can't start it more than once\r\n";
    private static final String USE_RESUME = "Use 'resume' instead\r\n";
    public static final String SIMULATION_WAS_INTERRUPTED = "Simulation was interrupted";
    public static final String STOPPED_RESET_STATE = "Simulation stopped, state have been reset";
    public static final String SIMULATION_IS_NOT_RUNNING = "Simulation is not running!";
    public static final String SIMULATION_RESUMED = "Simulation resumed";
    public static final String SIMULATION_IS_NOT_PAUSED = "Simulation is not paused!";
    public static final String SIMULATION_IS_NOT_RUNNING_USE_START_INSTEAD = "Simulation is not running - use 'start' instead";
    private final GameBoard board;
    private int moveCounter = 1;
    private final GameBoardRenderer renderer;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private boolean isRunning = false;
    private boolean isPaused = false;
    private Thread simulationThread;

    public Simulation(GameBoard board, List<Action> initActions, List<Action> turnActions) {
        this.board = board;
        renderer = new GameBoardRenderer(board);
        this.initActions = initActions;
        this.turnActions = turnActions;
    }

    private void nextTurn() {
        System.out.printf(ITERATION_COUNT, moveCounter);
        renderer.render();
        for (Action action : turnActions) {
            action.execute();
        }
        moveCounter++;
    }

    void startSimulation() {
        if (isRunning && !isPaused) {
            System.out.printf(SIMULATION_IS_RUNNING, REENTRY_START);
            return;
        }

        if (isRunning) {
            System.out.printf(SIMULATION_IS_RUNNING, USE_RESUME);
            return;
        }

        for (Action action : initActions) {
            action.execute();
        }

        isRunning = true;
        isPaused = false;

        simulationThread = new Thread(() -> {
            try {
                while (isRunning) {
                    if (!isPaused) {
                        nextTurn();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println(SIMULATION_WAS_INTERRUPTED);
                Thread.currentThread().interrupt();
            }
        });
        simulationThread.start();
    }

    public void pauseSimulation() {
        if (!isRunning || isPaused) {
            System.out.println("Simulation is not running or already paused!");
            return;
        }

        isPaused = true;
        System.out.println("Simulation paused");
    }

    public void resumeSimulation() {
        if (!isRunning) {
            System.out.println(SIMULATION_IS_NOT_RUNNING_USE_START_INSTEAD);
            return;
        }

        if (!isPaused) {
            System.out.println(SIMULATION_IS_NOT_PAUSED);
            return;
        }

        isPaused = false;
        System.out.println(SIMULATION_RESUMED);
    }

    public void stopSimulationAndResetState() {
        if (!isRunning) {
            System.out.println(SIMULATION_IS_NOT_RUNNING);
            return;
        }

        isRunning = false;
        isPaused = false;
        simulationThread.interrupt();
        System.out.println(STOPPED_RESET_STATE);
        resetSimulationState();
    }

    private void resetSimulationState() {
        board.clearEntities();
        moveCounter = 1;
    }

    public void printAndProcessMenu() {
        System.out.println("Select one of the simulation commands:");
        System.out.println("start  - begin simulation");
        System.out.println("p      - pause simulation");
        System.out.println("r      - resume paused simulation");
        System.out.println("s      - stop simulation completely");
        System.out.println("exit   - quit program");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            switch (input) {
                case "start":
                    startSimulation();
                    break;
                case "p":
                    pauseSimulation();
                    break;
                case "r":
                    resumeSimulation();
                    break;
                case "s":
                    stopSimulationAndResetState();
                    break;
                case "exit":
                    stopSimulationAndResetState();
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
}