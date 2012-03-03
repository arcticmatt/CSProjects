import java.util.*;

	/** Represents a node in the search tree. **/

public class Node {
	/* The board associated with this node. */
	private Board board = null;
	/* The score of this board */
	private int score;
	/* The children of this node. */
	private LinkedList<Node> children = null;
	private Node parent = null;
	/* parentMove gives index of the square where the previous piece was placed */
	private int parentMove;
	public Node() {}
	public void setBoard(Board b) {
		/* set the Node's board */
		board = b;
	}
	public Board getBoard() {
		/* Get the board associated with the node */
		return board;
	}
	public int getScore() {
		/* Get the score of this board */
		return score;
	}
	public void setScore(int i) {
		/* Set the score of this board */
		score = i;
	}
	public LinkedList<Node> getChildren() {
		return children;
	}
	public void setChildren(LinkedList<Node> c) {
		children = c;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node p) {
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
	
	public void print() {
		/* Prints a textual representation of the node */
		System.out.println("White Pieces:");
		MoveGen.printBitboard(getBoard().getWhitePieces());
		System.out.println("Black Pieces");
		MoveGen.printBitboard(getBoard().getBlackPieces());
	}
	
	public Node clone() {
		/* Returns a clone of this node. */
		Node clone = new Node();
		clone.setBoard(this.getBoard());
		clone.setScore(this.getScore());
		clone.setParent(this.getParent());
		clone.setChildren(this.getChildren());
		return clone;
	}
	
}
