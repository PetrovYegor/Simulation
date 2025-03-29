//package simulation.actions.setup_actions;
//
//import simulation.Coordinates;
//import simulation.EntityLimitSettings;
//import simulation.GameBoard;
//import simulation.actions.Action;
//import simulation.models.Tree;
//
//public class SetupTreeAction implements Action {
//    private final GameBoard board;
//
//    public SetupTreeAction(GameBoard board) {
//        this.board = board;
//    }
//
//    @Override
//    public void execute() {
//        for (int i = 0; i < EntityLimitSettings.TREE_LIMIT; i++) {
//            Coordinates randomFreeCoordinates = board.getFreeCoordinates();
//            int x = randomFreeCoordinates.getX();
//            int y = randomFreeCoordinates.getY();
//            board.setEntity(new Coordinates(x, y), new Tree(new Coordinates(x, y)));
//        }
//    }
//}