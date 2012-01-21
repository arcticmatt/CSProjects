/**
 * A class which can solve the Towers of Hanoi game.
 * 
 */
public class HanoiSolver {
  
  private HanoiGame hanoi;
  
  /**
   * Create a new HanoiSolver to solve a HanoiGame.
   * 
   * @param hanoi The HanoiGame to solve.
   */
  public HanoiSolver(HanoiGame hanoi) {
    this.hanoi = hanoi;
  }
  
  /**
   * Solve the encapsulated HanoiGame.
   */
  public void solve() {
	moveDisks(hanoi.getNumDisks(0), 0, 2, 1);
  }

  /** My algorithm solves the hanoi tower in f(n) = 2^n - 1 moves, where n specifies the
   * number of pieces originally on the 1st peg. To justify, note that the recursive
   * method moves n-1 pieces from the 1st peg to the second peg, then moves the last,
   * largest piece to the third peg. Finally, it moves all the pieces from the second
   * peg to the third peg. Thus, the number of moves for the n+1 solver is the following:
   * f(n+1) = 2 * f(n) + 1 (this is because we're moving an n size stack twice, and 
   * an extra single piece once). f(n) = 2^n - 1 satisfies this relation:
   * 2 *(2^n - 1) + 1 = 2^(n+1) - 1. 
   * 
   */
  
  private void moveDisks(int diskCount, int initialPeg, int finalPeg, int unusedPeg) {
	 /* Moves diskCount disks from the initialPeg to the finalPeg. UnusedPeg specifies
	   * the other, third, peg.
	   */
	  if (diskCount == 1) {
		  /* If we only have to move 1 disk, just move it and return. */
		  hanoi.makeMove(initialPeg, finalPeg);
		  return;
	  }
	  /* Move diskCount-1 disks to the unusedPeg, move the largest disk to the end,
	   * and finally move the rest of the disks from the unusedPeg to the finalPeg.
	   */
	  moveDisks(diskCount - 1, initialPeg, unusedPeg, finalPeg);
	  hanoi.makeMove(initialPeg, finalPeg);
	  moveDisks(diskCount - 1, unusedPeg, finalPeg, initialPeg);
  }
  
}
