
// You will want to rename this to contain your cluster username
// or another username unique to you.
// The easiest way in Eclipse to do this is to right-click on the file in the
// Package Explorer and choose "Refactor -> Rename". 

public class examplePlayer implements OthelloPlayer {

	@Override
	public void init(OthelloSide side) {
		// TODO Initialize your player here.
		// You will want to be able to keep track of the board state
		// and initialize anything else you will need.

	}

	@Override
	public Move doMove(Move opponentsMove, long millisLeft) {
		// TODO Do all the processing you need to return a legal move.
		// You will want to update the stored board state.
		// You must pass (return null) if there are no legal moves;
		// if there is at least one legal move, you must choose one to play,
		// and return a Move object containing it.
		return null;
	}
	
	// You may define as many other methods as you feel you need.

}
