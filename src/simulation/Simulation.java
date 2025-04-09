package simulation;

import simulation.actions.Action;

import java.util.List;

public class Simulation {
    private final String ITERATION_COUNT_MESSAGE = "The number of iterations of the game cycle: %d";
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
        System.out.printf(ITERATION_COUNT_MESSAGE, moveCounter);
        renderer.render();
        for (Action action : turnActions) {
            action.execute();
        }
        moveCounter++;
    }

    void startSimulation() throws InterruptedException {
        if (isRunning && !isPaused) {
            System.out.println("Simulation is already running!");
            return;
        }

        // Удалить Если симуляция на паузе - возобновляем
        if (isPaused) {
            resumeSimulation();
            return;
        }

        // Инициализация мира (только при первом запуске)
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
                System.out.println("Simulation was interrupted");
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

    public void resumeSimulation() throws InterruptedException {
        if (!isRunning) {
            System.out.println("Simulation is not running - use 'start' instead");
            return;
        }

        if (!isPaused) {
            System.out.println("Simulation is not paused!");
            return;
        }

        isPaused = false;
        System.out.println("Simulation resumed");
    }

    public void stopSimulation(){
        if (!isRunning) {
            System.out.println("Simulation is not running!");
            return;
        }

        isRunning = false;
        isPaused = false;
        simulationThread.interrupt();
        System.out.println("Simulation stopped");
    }
}