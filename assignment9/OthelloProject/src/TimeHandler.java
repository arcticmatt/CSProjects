
public class TimeHandler {
	public static int timeForMove(Board b, long millisLeft) {
		/* Returns the amount of time to be apportioned to this board */
		int movesLeft = 64 - b.getPieceCount();
		long averageTime = millisLeft / movesLeft;
		if (averageTime < 100) {
			return (int)averageTime;
		}
		return (int)averageTime - 50;
	}
}
