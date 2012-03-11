
public class Evaluator {
	public static int evaluate(Node n) {
		/* Evaluates the given board to determine the score of the board. */
		Board b = n.getBoard();
		int pieceDifferential = Long.bitCount(b.getWhitePieces()) - Long.bitCount(b.getBlackPieces());
		int mobilityDifferential = 0;
		return 0;
	}
	
	
	
}
