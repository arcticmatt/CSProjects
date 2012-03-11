
public class SearchTree {
	/** Represents a search tree. **/
	
	Node root;
	//TranspositionTable tTable;
	public int cutoffs = 0;
	
	public SearchTree(Node root) {
		/** root is the node we'll be searching **/
		this.root = root;
		//tTable = new TranspositionTable(Constants.transpositionTableSize);
	}

	public Node alphaBetaWithTimeLimit(int timeLimit) {
		/** Runs alpha beta at successive depths until the time limit (in ms) is exceeded **/
		/*
		long maxTime = System.currentTimeMillis() + timeLimit;
		int depth = 1;
		Node bestNode, nextBestNode = null;
		do {
			root.setChildren(null);
			root.setAlpha(-Constants.INF);
			root.setBeta(Constants.INF);
			bestNode = nextBestNode;
			nextBestNode = alphaBetaBestNode(depth++, maxTime);
		} while (System.currentTimeMillis() < maxTime);
		System.out.println("reached depth " + depth);
		return bestNode;
		*/
		long maxTime = System.currentTimeMillis() + 10000;
		return alphaBetaBestNode(7, maxTime);
	}
	
	private Node alphaBetaBestNode(int depth, long maxTime) {
		/** Runs the alpha beta algorithm, returning the best node found **/
		/* Generate all the values */
		alphaBeta(depth, root, maxTime);
		if (root.isMaximizing()) {
			for (Node n : root.getChildren()) {
				if (n.getBeta() == root.getAlpha()) {
					return n;
				}
			}
		} else {
			for (Node n : root.getChildren()) {
				if (n.getAlpha() == root.getBeta()) {
					return n;
				}
			}
		}
		return null;
	}
	
	private void alphaBeta(int depth, Node selectedNode, long maxTime) {
		/* Runs the alpha-beta algorithm on the tree, searching it up to a given depth. If we
		 * exceed maxTime, the subroutine returns. */
		if (System.currentTimeMillis() > maxTime) {
			/* Time limit exceeded */
			return;
		}
		if (depth == 0) {
			/* If the depth is 0, set our alpha or beta according to the heuristic */
			int score = Evaluator.evaluate(selectedNode);
			if (selectedNode.isMaximizing()) {
				/* If we're maximizing, set alpha accordingly. */
				selectedNode.setAlpha(score);
			} else {
				selectedNode.setBeta(score);
			}
			/* Don't go any more depths */
			return;
		}
		
		/* Generate the children */
		MoveGen.generateChildren(selectedNode);
		
		int alpha = selectedNode.getAlpha();
		int beta = selectedNode.getBeta();
		
		/* If we're maximizing, run through the children until we find a cutoff */
		if (selectedNode.isMaximizing()) {
			for (Node N : selectedNode.getChildren()) {
				/* Copy over the alpha / beta values */
				N.setAlpha(alpha);
				N.setBeta(beta);
				/* Generate the alpha, beta values of the child */
				alphaBeta(depth-1, N, maxTime);
				/* Check if we have a new best maximizing move */
				if (N.getBeta() > alpha) {
					alpha = N.getBeta();
				}
				if (alpha >= beta) {
					/* Cutoff! */
					cutoffs++;
					break;
				}
			}
			
		} else {
			for (Node N : selectedNode.getChildren()) {
				/* Copy over the alpha / beta values */
				N.setAlpha(alpha);
				N.setBeta(beta);
				/* Generate the alpha, beta values of the child */
				alphaBeta(depth-1, N, maxTime);
				/* Check if we have a new best maximizing move */
				if (N.getAlpha() < beta) {
					beta = N.getAlpha();
				}
				if (alpha >= beta) {
					/* Cutoff! */
					cutoffs++;
					break;
				}
			}
		}
		/* Set the alpha / beta values to the values generated. */
		selectedNode.setAlpha(alpha);
		selectedNode.setBeta(beta);
	}
	
	
	
	public Node minMaxBestNode(int depth) {
		/** Runs the minMax algorithm, returning the best node found **/
		minMax(depth, root);
		if (root.getBoard().getMovingSide() == Constants.WHITE) {
			/* Then return the maximum child */
			return getMaxNode(root);
		} else {
			return getMinNode(root);
		}
	}
	
	private void minMax(int depth, Node selectedNode) {
		/* Generates the tree up to a certain depth & search it with minMax; returns the 
		 * best move.
		  */
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
	
	private Node getMinNode(Node n) {
		/** Returns the child of the given node with the lowest score **/
		int minScore = 99999999;
		Node minNode = null;
		for (Node i : n.getChildren()) {
			if (i.getScore() < minScore) {
				minScore = i.getScore();
				minNode = i;
			}
		}
		return minNode;
	}
	
	private Node getMaxNode(Node n) {
		/** Returns the child of the given node with the highest score **/
		int maxScore = -99999999;
		Node maxNode = null;
		for (Node i : n.getChildren()) {
			if (i.getScore() > maxScore) {
				maxScore = i.getScore();
				maxNode = i;
			}
		}
		return maxNode;
	}
	
	private int getMinScore(Node n) {
		/** Returns the minimum score of a child of the node **/
		int minScore = 99999999;
		for (Node i : n.getChildren()) {
			if (i.getScore() < minScore) {
				minScore = i.getScore();
			}
		}
		return minScore;
	}
	
	private int getMaxScore(Node n) {
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
