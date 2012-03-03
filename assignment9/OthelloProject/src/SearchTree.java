
public class SearchTree {
	/** Represents a search tree. **/
	public static int totalNodes = 0;
	
	Node root;
	
	public SearchTree(Node root) {
		/** root is the node we'll be searching **/
		this.root = root;
	}
	
	public static void minMax(int depth, Node selectedNode) {
		/* Generates the tree up to a certain depth & search it with minMax; returns the 
		 * best move.
		  */
		totalNodes += 1;
		if (depth == 0) {
			/* If the depth is 0, set the score to what the heuristic gives */
			int score = Evaluator.evaluate(selectedNode);
			selectedNode.setScore(score);
			return;
		}
		/* Otherwise, generate all the children & run minmax on them */
		MoveGen.generateChildren(selectedNode);
		for (Node i : selectedNode.getChildren()) {
			minMax(depth-1, i);
		}
		/* finally, set the score of this board to be the max or min of its children */
		if (selectedNode.getBoard().getMovingSide() == Constants.WHITE) {
			/* then it's maximizing */
			int score = getMaxScore(selectedNode);
			selectedNode.setScore(score);
		} else {
			int score = getMinScore(selectedNode);
			selectedNode.setScore(score);
		}
		
	}
	
	private static int getMinScore(Node n) {
		/** Returns the minimum score of a child of the node **/
		int minScore = 99999999;
		for (Node i : n.getChildren()) {
			if (i.getScore() < minScore) {
				minScore = i.getScore();
			}
		}
		return minScore;
	}
	
	private static int getMaxScore(Node n) {
		/** Returns the child of n with the highest score **/
		int maxScore = -99999999;
		for (Node i : n.getChildren()) {
			if (i.getScore() > maxScore) {
				maxScore = i.getScore();
			}
		}
		return maxScore;
	}
	
}
