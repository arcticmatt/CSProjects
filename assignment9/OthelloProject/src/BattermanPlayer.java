


public class BattermanPlayer implements OthelloPlayer {
	
	/* Represents the current node in the game */
	private Node currentNode;
	private byte ourColor;
	
	public BattermanPlayer() {}
	public void init(OthelloSide side) {
		/* Initialize the currentNode */
		initializeCurrentNode();
		/* Set our color. */
		if (side == OthelloSide.BLACK) {
			ourColor = Constants.BLACK;
		} else {
			ourColor = Constants.WHITE;
		}
	}
	
	private void initializeCurrentNode() {
		/* Initialize the currentNode */
		currentNode = new Node();
		long whitePieces = Masks.bitAt[28] | Masks.bitAt[35];
		long blackPieces = Masks.bitAt[27] | Masks.bitAt[36];
		Board b = new Board(whitePieces, blackPieces, Constants.WHITE);
		currentNode.setBoard(b);
	}
	
	public Move doMove(Move opponentsMove, long millisLeft) {
		/* Update the current node */
		updateCurrentNode(opponentsMove);
		MoveGen.generateChildren(currentNode);
		int moveIndex = currentNode.getChildren().get(0).getParentMove();
		currentNode.getChildren().get(0).print();
		Move nextMove = indexToMove(moveIndex);
		updateCurrentNode(nextMove);
		return nextMove;
	}
	
	private void updateCurrentNode(Move m) {
		/** Updates the current node given a move. **/
		/* if the move isn't null, then update currentNode accordingly */
		if (m != null) {
			/* First, generate the children of the current node */
			MoveGen.generateChildren(currentNode);
			/* Now loop through all the children until we identify the one
			 * associated with the move.
			 */
			int moveIndex = moveToIndex(m);
			for (Node n : currentNode.getChildren()) {
				if (n.getParentMove() == moveIndex) {
					/* remove the parent, and currentNode is the child */
					n.setParent(null);
					currentNode = n;
					MoveGen.printBitboard(currentNode.getBoard().getBlackPieces());
					MoveGen.printBitboard(currentNode.getBoard().getWhitePieces());
					return;
				}
			}
		} else {
			System.out.println("here");
			/* if the move is null, the the opponent forfeited his turn;
			* Change the currentNode's color to ours, and continue. */
			Board oldBoard = currentNode.getBoard();
			Board newBoard = new Board(oldBoard.getWhitePieces(),
					oldBoard.getBlackPieces(), oldBoard.getMovingSide());
			currentNode.setBoard(newBoard);
			//currentNode.print();
			System.out.println(newBoard.equalTo(oldBoard));
			System.out.println(oldBoard.getMovingSide());
		}
	}
	
	private Move indexToMove(int a) {
		/* Converts a movement index to an instance of the move class */
		int x = a % 8;
		int y = 7 - a / 8;
		return new Move(x,y);
	}
	
	
	private int moveToIndex(Move m) {
		int Y = 7 - m.getY();
		int X = m.getX();
		return 8*Y + X;
	}
	
}
