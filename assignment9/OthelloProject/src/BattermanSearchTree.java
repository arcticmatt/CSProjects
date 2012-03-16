import java.util.*;

public class BattermanSearchTree {
	/** Represents a search tree. **/
	
	BattermanNode root;
	//TranspositionTable tTable;
	public int cutoffs = 0;
	public int examinedNodes = 0;
	
	public BattermanSearchTree(BattermanNode root) {
		/** root is the node we'll be searching **/
		this.root = root;
		//tTable = new TranspositionTable(Constants.transpositionTableSize);
	}

	public BattermanNode alphaBetaWithTimeLimit(int timeLimit) {
		/** Runs alpha beta at successive depths until the time limit (in ms) is exceeded **/
		
		long maxTime = System.currentTimeMillis() + timeLimit;
		int depth = 1;
		BattermanNode bestNode, nextBestNode = null;
		do {
			root.setAlpha(-BattermanConstants.INF);
			root.setBeta(BattermanConstants.INF);
			root.setChildren(null);
			bestNode = nextBestNode;
			nextBestNode = alphaBetaBestNode(depth++, maxTime);
		} while (System.currentTimeMillis() < maxTime && depth < BattermanConstants.MAXDEPTH);
		System.out.println("reached depth " + depth);
		return bestNode;
		
		
		//long maxTime = System.currentTimeMillis() + 10000;
		//return alphaBetaBestNode(9, maxTime);
	}
	
	private BattermanNode alphaBetaBestNode(int depth, long maxTime) {
		/** Runs the alpha beta algorithm, returning the best node found **/
		/* Generate all the values */
		alphaBeta(depth, root, maxTime);
		if (root.isMaximizing()) {
			for (BattermanNode n : root.getChildren()) {
				if (n.getBeta() == root.getAlpha()) {
					return n;
				}
			}
		} else {
			for (BattermanNode n : root.getChildren()) {
				if (n.getAlpha() == root.getBeta()) {
					return n;
				}
			}
		}
		return null;
	}
	
	private void alphaBeta(int depth, BattermanNode selectedNode, long maxTime) {
		/* Runs the alpha-beta algorithm on the tree, searching it up to a given depth. If we
		 * exceed maxTime, the subroutine returns. */
		if (System.currentTimeMillis() > maxTime) {
			/* Time limit exceeded */
			return;
		}
		examinedNodes++;
		
		if (depth == 0) {
			/* If the depth is 0, set our alpha or beta according to the heuristic */
			int score = BattermanEvaluator.evaluate(selectedNode);
			if (selectedNode.isMaximizing()) {
				/* If we're maximizing, set alpha accordingly. */
				selectedNode.setAlpha(score);
			} else {
				selectedNode.setBeta(score);
			}
			/* Don't go any more depths */
			return;
		}
		
		if (depth == 1 || selectedNode.getChildren() == null) {
			/* Generate the children */
			BattermanMoveGen.generateChildren(selectedNode);
		}
		
		int alpha = selectedNode.getAlpha();
		int beta = selectedNode.getBeta();
		
		LinkedList<BattermanNode> goodNodes = new LinkedList<BattermanNode>();
		
		/* If we're maximizing, run through the children until we find a cutoff */
		Iterator<BattermanNode> nodeIterator = selectedNode.getChildren().iterator();
		if (selectedNode.isMaximizing()) {
			while (nodeIterator.hasNext()) {
				BattermanNode N = nodeIterator.next();
				/* Copy over the alpha / beta values */
				N.setAlpha(alpha);
				N.setBeta(beta);
				/* Generate the alpha, beta values of the child */
				alphaBeta(depth-1, N, maxTime);
				/* Check if we have a new best maximizing move */
				if (N.getBeta() > alpha) {
					alpha = N.getBeta();
					/* If we get a good move, add it to our list of good nodes / remove
					 * it from the children.
					 */
					goodNodes.add(N);
					nodeIterator.remove();
				}
				if (alpha >= beta) {
					/* Cutoff! */
					cutoffs++;
					break;
				}
			}
			
		} else {
			while (nodeIterator.hasNext()) {
				BattermanNode N = nodeIterator.next();
				/* Copy over the alpha / beta values */
				N.setAlpha(alpha);
				N.setBeta(beta);
				/* Generate the alpha, beta values of the child */
				alphaBeta(depth-1, N, maxTime);
				/* Check if we have a new best maximizing move */
				if (N.getAlpha() < beta) {
					beta = N.getAlpha();
					goodNodes.add(N);
					nodeIterator.remove();
				}
				if (alpha >= beta) {
					/* Cutoff! */
					cutoffs++;
					break;
				}
			}
		}
		/* Change our move ordering */
		selectedNode.getChildren().addAll(0, goodNodes);
		/* Set the alpha / beta values to the values generated. */
		selectedNode.setAlpha(alpha);
		selectedNode.setBeta(beta);
	}
	
	
	
	public BattermanNode minMaxBestNode(int depth) {
		/** Runs the minMax algorithm, returning the best node found **/
		minMax(depth, root);
		if (root.getBoard().getMovingSide() == BattermanConstants.WHITE) {
			/* Then return the maximum child */
			return getMaxNode(root);
		} else {
			return getMinNode(root);
		}
	}
	
	private void minMax(int depth, BattermanNode selectedNode) {
		/* Generates the tree up to a certain depth & search it with minMax; returns the 
		 * best move.
		  */
		if (depth == 0) {
			/* If the depth is 0, set the score to what the heuristic gives */
			int score = BattermanEvaluator.evaluate(selectedNode);
			selectedNode.setScore(score);
			return;
		}
		/* Otherwise, generate all the children & run minmax on them */
		BattermanMoveGen.generateChildren(selectedNode);
		for (BattermanNode i : selectedNode.getChildren()) {
			minMax(depth-1, i);
		}
		/* finally, set the score of this board to be the max or min of its children */
		if (selectedNode.getBoard().getMovingSide() == BattermanConstants.WHITE) {
			/* then it's maximizing */
			int score = getMaxScore(selectedNode);
			selectedNode.setScore(score);
		} else {
			int score = getMinScore(selectedNode);
			selectedNode.setScore(score);
		}
		
		
	}
	
	private BattermanNode getMinNode(BattermanNode n) {
		/** Returns the child of the given node with the lowest score **/
		int minScore = 99999999;
		BattermanNode minNode = null;
		for (BattermanNode i : n.getChildren()) {
			if (i.getScore() < minScore) {
				minScore = i.getScore();
				minNode = i;
			}
		}
		return minNode;
	}
	
	private BattermanNode getMaxNode(BattermanNode n) {
		/** Returns the child of the given node with the highest score **/
		int maxScore = -99999999;
		BattermanNode maxNode = null;
		for (BattermanNode i : n.getChildren()) {
			if (i.getScore() > maxScore) {
				maxScore = i.getScore();
				maxNode = i;
			}
		}
		return maxNode;
	}
	
	private int getMinScore(BattermanNode n) {
		/** Returns the minimum score of a child of the node **/
		int minScore = 99999999;
		for (BattermanNode i : n.getChildren()) {
			if (i.getScore() < minScore) {
				minScore = i.getScore();
			}
		}
		return minScore;
	}
	
	private int getMaxScore(BattermanNode n) {
		/** Returns the child of n with the highest score **/
		int maxScore = -99999999;
		for (BattermanNode i : n.getChildren()) {
			if (i.getScore() > maxScore) {
				maxScore = i.getScore();
			}
		}
		return maxScore;
	}
	
}
