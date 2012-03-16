import java.util.*;

public class TestGame
{
   public static void main(String[] args)
   {

	   
	   OthelloPlayer p1 = new BattermanPlayer();
		  OthelloPlayer p2 = new BattermanPlayer();
		  // p3 = new BetterPlayer();
		  OthelloPlayer o2 = new OthelloDisplay();
		  OthelloDisplay o = new OthelloDisplay();
		  //OthelloPlayer o2 = new OthelloDisplay();
		  OthelloGame g = new OthelloGame(p2, p1, o, 120000);
		  g.run();

   }
   

   
}
