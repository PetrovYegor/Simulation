package simulation.actions;

import java.util.Random;

public final class ActionUtils {
    private ActionUtils(){}

    private static final int MIN_HEALTH_VALUE_EXCLUSIVE = 1;
    private static final int MAX_HEALTH_VALUE_EXCLUSIVE = 7;
    private static final int MIN_SPEED_VALUE_EXCLUSIVE = 1;
    private static final int MAX_SPEED_VALUE_EXCLUSIVE = 6;
    private static final int MIN_ATTACK_POWER_VALUE_EXCLUSIVE = 1;
    private static final int MAX_ATTACK_POWER_VALUE_EXCLUSIVE = 3;

    public static final int HERBIVORE_LIMIT = 3;
    public static final int GRASS_LIMIT = 5;
    public static final int ROCK_LIMIT = 3;
    public static final int PREDATOR_LIMIT = 1;
    public static final int TREE_LIMIT = 5;

    private static final Random random = new Random();

    public static int getRandomHealth() {
        return random.nextInt(MIN_HEALTH_VALUE_EXCLUSIVE, MAX_HEALTH_VALUE_EXCLUSIVE);
    }

    public static int getRandomSpeed() {
        return random.nextInt(MIN_SPEED_VALUE_EXCLUSIVE, MAX_SPEED_VALUE_EXCLUSIVE);
    }

    public static int getRandomAttackPower() {
        return random.nextInt(MIN_ATTACK_POWER_VALUE_EXCLUSIVE, MAX_ATTACK_POWER_VALUE_EXCLUSIVE);
    }
}
