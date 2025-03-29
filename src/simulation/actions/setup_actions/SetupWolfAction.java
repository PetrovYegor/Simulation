//package simulation.actions.setup_actions;
//
//import simulation.Coordinates;
//import simulation.EntityLimitSettings;
//import simulation.GameBoard;
//import simulation.actions.Action;
//import simulation.models.Creature;
//import simulation.models.Wolf;
//
//public class SetupWolfAction implements Action {
//    private final GameBoard board;
//
//    public SetupWolfAction(GameBoard board) {
//        this.board = board;
//    }
//
//    @Override
//    public void execute() {
//        for (int i = 0; i < EntityLimitSettings.PREDATOR_LIMIT; i++) {
//            Coordinates randomFreeCoordinates = board.getFreeCoordinates();
//            int x = randomFreeCoordinates.getX();
//            int y = randomFreeCoordinates.getY();
//            board.setEntity(new Coordinates(x, y), new Wolf(new Coordinates(x, y), Creature.getRandomSpeed(), Creature.getRandomHealth(), Creature.getRandomAttackPower()));
//        }
//    }
//}