import java.util.*;

public class TestGame
{
   public static void main(String[] args)
   {

	  int whiteWins =0, blackWins = 0;
//	  for (int i = 0; i < 50; i++) {
	  // Replace p1 and p2 with constructors for AIs you want to test.
      OthelloPlayer p1 = new BattermanPlayer();
      //OthelloPlayer p2 = new ConstantTimePlayer();
      OthelloPlayer p2 = new BetterPlayer();
     // OthelloTextObserver o = new OthelloTextObserver();
      OthelloDisplay o = new OthelloDisplay();
      //Uncomment this if you want to play against an AI as White.
      OthelloPlayer o2 = new OthelloDisplay();
      OthelloGame g = new OthelloGame(p1, p2, o, 20000);
      g.run();
 /*     
      if (((OthelloTextObserver)o).whiteWon()) {
    	  whiteWins++;
      } else {
    	  blackWins ++;
      }
      
	  }
	*/  
	  System.out.println("White wins " + whiteWins + " Black wins " + blackWins);
      //Uncomment this if you want to play against an AI as black.
      //OthelloPlayer o2 = new OthelloDisplay();
      //OthelloGame g = new OthelloGame(p1, p2, o);
      
      //Comment this if playing against an AI.
      //By default, we put two AIs against each other.
      //OthelloGame g = new OthelloGame(p1, p2, o);
      
     // runTests();
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
	   
	   /*
	   long x = System.currentTimeMillis();
	   System.out.println(System.currentTimeMillis());
	   while (System.currentTimeMillis() - x < 1000) { }
	   System.out.println(System.currentTimeMillis());
	   
	   
	   Node testNode = new Node();
	   long whitePieces = Masks.bitAt[28] | Masks.bitAt[35];
	   long blackPieces = Masks.bitAt[27] | Masks.bitAt[36];
	   System.out.println(MoveGen.moveCount(whitePieces, blackPieces)); */
	   /*
	   Board b = new Board(whitePieces, blackPieces, Constants.BLACK);
	   testNode.setBoard(b);
	   System.out.println("starting");
	   for (int i = 0; i < 5000000; i++) {
	   MoveGen.generateChildren(testNode);
	   testNode.setChildren(null);
	   }
	   
	  // System.out.println(testNode.getChildren().size());
	   //boardTestboard = testNode.getChildren().peek().getBoard();
	   ///MoveGen.printBitboard(testNode.getChildren().get(0).getBoard().getWhitePieces());
	   //MoveGen.printBitboard(testNode.getChildren().get(0).getBoard().getBlackPieces());
	   System.out.println("ending");
	   
	   StringFunction x = new StringFunction() {
		
		@Override
		public int function(String param) {
			// TODO Auto-generated method stub
			return 5;
		} 
	};
	  
	System.out.println(x.function(""));
	*/
   }
   
   interface StringFunction {
	    int function(String param);
	}
   
}
