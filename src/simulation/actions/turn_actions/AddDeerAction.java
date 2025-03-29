//package simulation.actions.turn_actions;
//
//import simulation.GameBoard;
//import simulation.actions.Action;
//import simulation.actions.setup_actions.SetupHerbivoreAction;
//
//public class AddDeerAction implements Action {
//    private final GameBoard board;
//
//    public AddDeerAction(GameBoard board) {
//        this.board = board;
//    }
//
//    @Override
//    public void execute() {
//        if (!board.isHerbivoreEnough()) {
//            new SetupHerbivoreAction(board).execute();
//        }
//    }
//}
