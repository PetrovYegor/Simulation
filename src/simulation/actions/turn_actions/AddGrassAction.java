//package simulation.actions.turn_actions;
//
//import simulation.GameBoard;
//import simulation.actions.Action;
//import simulation.actions.setup_actions.SetupGrassAction;
//
//public class AddGrassAction implements Action {
//    private final GameBoard board;
//
//    public AddGrassAction(GameBoard board) {
//        this.board = board;
//    }
//
//    @Override
//    public void execute() {
//        if (!board.isGrassEnough()) {
//            new SetupGrassAction(board).execute();
//        }
//    }
//}
