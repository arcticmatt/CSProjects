
public class Evaluator {
	public static int evaluate(Node n) {
		/* Evaluates the given board to determine the score of the board. */
		Board b = n.getBoard();
		int score = Long.bitCount(b.getWhitePieces()) - Long.bitCount(b.getBlackPieces());
		return score;
	}
}
