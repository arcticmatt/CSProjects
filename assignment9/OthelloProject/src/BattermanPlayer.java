


public class BattermanPlayer implements OthelloPlayer {
	
	/* Represents the current node in the game */
	private Node currentNode;
	private byte ourColor;
	private byte enemyColor;
	public BattermanPlayer() {}
	public void init(OthelloSide side) {
		/* Initialize the currentNode */
		initializeCurrentNode();
		System.out.println(side);
		/* Set our color. */
		if (side == OthelloSide.BLACK) {
			ourColor = Constants.BLACK;
			enemyColor = Constants.WHITE;
		} else {
			ourColor = Constants.WHITE;
			enemyColor = Constants.BLACK;
		}
	}
	
	public Move generateNextMove() {
		/** The main move generation function **/
		SearchTree sTree = new SearchTree(currentNode);
		Node bestNode = sTree.alphaBetaWithTimeLimit(500);
		System.out.println("Cutoffs: " + sTree.cutoffs);
		//System.out.println( "Best forced score: " + currentNode.getBeta());
		
		if (bestNode.wasCreatedByPass()) {
			/* If the best move was a passing move, 
			 * return null. */
			System.out.println("Passing");
			return null;
		}
		int moveIndex = bestNode.getParentMove();
		Move nextMove = indexToMove(moveIndex);
		return nextMove; 
		
	}
	
	
	public Move doMove(Move opponentsMove, long millisLeft) {
		/* Update the current node */
		/* first, do the opponents move */
		setCurrentNodeMovingSide(enemyColor);
		if (opponentsMove != null) {
			updateCurrentNodeWithMove(opponentsMove);
		}
		currentNode.print();
		/* Now, obtain our move & update currentNode with our move */
		setCurrentNodeMovingSide(ourColor);
		Move nextMove = generateNextMove();
		if (nextMove != null) {
			updateCurrentNodeWithMove(nextMove);
		}
		System.out.println("I am playing " + nextMove);
		currentNode.print();
		//System.out.println("Hash: " + currentNode.getBoard().getHash());
		return nextMove;
	}
	
	private void updateCurrentNodeWithMove(Move m) {
		/** Updates the current node given a move. **/
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
				//currentNode.print();
				return;
			}
		}
		
	}
		/* if the move is null, don't do anything. */
		

	
	private void initializeCurrentNode() {
		/* Initialize the currentNode */
		currentNode = new Node();
		long whitePieces = Masks.bitAt[28] | Masks.bitAt[35];
		long blackPieces = Masks.bitAt[27] | Masks.bitAt[36];
		Board b = new Board(whitePieces, blackPieces, Constants.BLACK);
		currentNode.setBoard(b);
	}
	
	private void setCurrentNodeMovingSide(byte x) {
		/* Sets the current node's moving side. */
		currentNode.getBoard().setMovingSide(x);
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
