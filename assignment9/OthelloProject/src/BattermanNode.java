import java.util.*;

	/** Represents a node in the search tree. **/

public class BattermanNode {
	/* The board associated with this node. */
	private BattermanBoard board = null;
	/* The score of this board -- score used for minMax searches */
	private int score;
	/* alpha, beta variables are used for the alpha beta pruning search. */
	private int alpha = -BattermanConstants.INF;
	private int beta = BattermanConstants.INF;
	/* The children of this node. */
	private LinkedList<BattermanNode> children = null;
	private BattermanNode parent = null;
	/* parentMove gives index of the square where the previous piece was placed */
	private int parentMove;
	/* Denotes whether this node was created by a pass from the previous node */
	private boolean createdByPass = false;
	
	
	public BattermanNode() {}
	public void setBoard(BattermanBoard b) {
		/* set the Node's board */
		board = b;
	}

	
	public int getAlpha() {
		/* Returns the alpha of the node */
		return alpha;
	}
	
	public void setAlpha(int x) {
		/* Sets a new value for alpha */
		alpha = x;
	}
	
	public int getBeta() {
		/* Returns the beta value of the node */
		return beta;
	}
	
	public void setBeta(int x) {
		/* Sets beta for the node */
		beta = x;
	}
	
	public BattermanBoard getBoard() {
		/* Get the board associated with the node */
		return board;
	}
	
	public void setCreatedByPass(boolean b) {
		createdByPass = true;
	}
	
	public boolean wasCreatedByPass() {
		return createdByPass;
	}
	
	public int getScore() {
		/* Get the score of this board */
		return score;
	}
	public void setScore(int i) {
		/* Set the score of this board */
		score = i;
	}
	public LinkedList<BattermanNode> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<BattermanNode> c) {
		children = c;
	}
	public BattermanNode getParent() {
		return parent;
	}
	public void setParent(BattermanNode p) {
		parent = p;
	}
	
	public void setParentMove(int x) {
		/* Sets the parent move; i.e. the move which created this node. */
		parentMove = x;
	}
	
	public int getParentMove() {
		/* Returns the parent move which generated this node */
		return parentMove;
	}
	
	public boolean isMaximizing() {
		/* Returns whether the maximizer is to move at the node */
		return (getBoard().getMovingSide() == BattermanConstants.WHITE);
	}
	
	public boolean isMinimizing() {
		/* Returns whether the minimizer is moving at this board */
		return (getBoard().getMovingSide() == BattermanConstants.BLACK);
	}
	
	public void print() {
		/** Prints the node in a human-readable format. Useful for debugging. **/
		long whitePieces = getBoard().getWhitePieces();
		long blackPieces = getBoard().getBlackPieces();
		System.out.println();
		for (int i = 7; i >= 0; i--) {
			String output = "";
			for (int j = 0; j < 8; j++) {
				long selectedSquare = BattermanMasks.bitAt[i * 8 + j];
				if ((selectedSquare & whitePieces) != 0) {
					output += "W";
				} else if ((selectedSquare & blackPieces) != 0) {
					output += "B";
				} else {
					output += "_";
				}
			}
			System.out.println(output);
		}
		System.out.println();
	}
	
	public BattermanNode clone() {
		/* Returns a clone of this node. */
		BattermanNode clone = new BattermanNode();
		clone.setBoard(this.getBoard());
		clone.setScore(this.getScore());
		clone.setParent(this.getParent());
		clone.setChildren(this.getChildren());
		return clone;
	}
	
}
