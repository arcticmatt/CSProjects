/**
 * An observer that prints all moves to stdout.
 * <p>
 * $Id: OthelloTextObserver.java,v 1.6 2005/02/17 07:56:11 plattner Exp $
 *
 * @author Aaron Plattner
 **/
 
public class OthelloTextObserver implements OthelloObserver
{
	private OthelloResult result = null;
   /** The player who's move is next.
    **/
   private OthelloSide player = OthelloSide.BLACK;

   public void OnMove(Move m, long blackTimeout, long whiteTimeout)
   {
      System.out.println(player + ": " + m);
      System.out.println("Time left: black: " + OthelloUtil.showTime(blackTimeout) + " white: " + OthelloUtil.showTime(whiteTimeout));
      player = player.opposite();
   }

   public boolean whiteWon() {
	   return (result.getWinner() == OthelloSide.WHITE);
   }
   
   public boolean blackWon() {
	   return (result.getWinner() == OthelloSide.BLACK);
   }
   
   public void OnGameOver(OthelloResult r)
   {
	   result = r;
      System.out.println("Game result: " + r);

      // If there was a runtime error, print the stack trace
      if(r.error instanceof ErrorException)
      {
         ErrorException e = ((ErrorException)r.error);
         Throwable playerException = e.error;
         playerException.printStackTrace();
      }
   }
}
