import java.util.*;

public class TestGame
{
   public static void main(String[] args)
   {

	   
	   OthelloPlayer p1 = new BattermanPlayer();
		  OthelloPlayer p2 = new BattermanPlayer();
		  OthelloPlayer p3 = new BetterPlayer();
		  OthelloPlayer o2 = new OthelloDisplay();
		  OthelloDisplay o = new OthelloDisplay();
		  //OthelloPlayer o2 = new OthelloDisplay();
		  OthelloGame g = new OthelloGame(p1, p2, o, 120000);
		  g.run();
 /*     
      if (((OthelloTextObserver)o).whiteWon()) {
    	  whiteWins++;
      } else {
    	  blackWins ++;
      }
      
	  }
	*/  
   }
   
   private static void runTests() {
	   /** Testing method. **/
	   
	   LinkedList<String> x = new LinkedList<String>();
	   x.add("11111");
	   x.add("22");
	   x.add("3");
	   x.add("444");
	   LinkedList<String> x2 = new LinkedList<String>();
	   Iterator<String> itr = x.iterator();
	   while (itr.hasNext()) {
		   String y = itr.next();
		   if (y.length() > 2) {
		   itr.remove();
		   x2.add(y); 
		   }
		   //x.addFirst(y);
		 //  itr.
		  //itr.
		   System.out.println(y);
	   }
	   
	   x2.addAll(0, x);
	   
	   for (String i : x2) {
		   System.out.println(i);
	   }
	   
   }
   
   interface StringFunction {
	    int function(String param);
	}
   
}
