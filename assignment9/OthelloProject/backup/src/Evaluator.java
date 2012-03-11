
public class Evaluator {
	public static int evaluate(Node n) {
		/* Evaluates the given board to determine the score of the board. */
		Board b = n.getBoard();
		long whitePieces = b.getWhitePieces();
		long blackPieces = b.getBlackPieces();
		int pieceDifferential = Long.bitCount(whitePieces) - Long.bitCount(blackPieces);
		int mobilityDifferential = MoveGen.moveCount(whitePieces, blackPieces) - MoveGen.moveCount(blackPieces, whitePieces);
		int cornerDifferential = Long.bitCount(whitePieces & Masks.corners) - Long.bitCount(blackPieces & Masks.corners);
		return 7*cornerDifferential + pieceDifferential + 2*mobilityDifferential;
	}
	
	
}
