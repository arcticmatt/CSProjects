


public class BattermanPlayer implements OthelloPlayer {
	
	/* Represents the current node in the game */
	private Node currentNode;
	private byte ourColor;
	private byte enemyColor;
	public BattermanPlayer() {}
	public void init(OthelloSide side) {
		/* Initialize the currentNode */
		initializeCurrentNode();
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
		
		//SearchTree tree = new SearchTree(currentNode);
		SearchTree.totalNodes = 0;
		SearchTree.minMax(6, currentNode);
		System.out.println("Examined: " + SearchTree.totalNodes);
		Node bestNode = null;
		int bestMove = -9999999;
		for (Node i : currentNode.getChildren()) {
			if (i.getScore() > bestMove) {
				bestMove = i.getScore();
				bestNode = i;
			}
		}
		System.out.println( "Best forced score: " + bestMove);
		int moveIndex = bestNode.getParentMove();
		Move nextMove = indexToMove(moveIndex);
		return nextMove; 
		
		
		/*
		MoveGen.generateChildren(currentNode);
		int moveIndex = currentNode.getChildren().get(0).getParentMove();
		currentNode.getChildren().get(0).print();
		Move nextMove = indexToMove(moveIndex);
		return nextMove;
		*/
	}
	
	
	public Move doMove(Move opponentsMove, long millisLeft) {
		/* Update the current node */
		/* first, do the opponents move */
		setCurrentNodeMovingSide(enemyColor);
		updateCurrentNode(opponentsMove);
		/* Now, obtain our move & update currentNode with our move */
		setCurrentNodeMovingSide(ourColor);
		Move nextMove = generateNextMove();
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
					return;
				}
			}
		}
		/* if the move is null, don't do anything. */
		
	}
	
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
