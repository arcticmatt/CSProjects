
public class BattermanEvaluator {
	public static int evaluate(BattermanNode n) {
		/* Evaluates the given board to determine the score of the board. */
		BattermanBoard b = n.getBoard();
		long whitePieces = b.getWhitePieces();
		long blackPieces = b.getBlackPieces();
		int pieceDifferential = Long.bitCount(whitePieces) - Long.bitCount(blackPieces);
		int mobilityDifferential = BattermanMoveGen.moveCount(whitePieces, blackPieces) - BattermanMoveGen.moveCount(blackPieces, whitePieces);
		int cornerDifferential = Long.bitCount(whitePieces & BattermanMasks.corners) - Long.bitCount(blackPieces & BattermanMasks.corners);
		int frontierDifferential = BattermanMoveGen.frontierSquaresCount(whitePieces, blackPieces) - BattermanMoveGen.frontierSquaresCount(blackPieces, whitePieces);
		if ((Long.bitCount(whitePieces) + Long.bitCount(blackPieces)) == 64) {
			return pieceDifferential;
		}
		return 28*cornerDifferential + 4*pieceDifferential + 8*mobilityDifferential - frontierDifferential;
	}
	
	
}
