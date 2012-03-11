public class TestGame
{
   public static void main(String[] args)
   {

	  int whiteWins =0, blackWins = 0;
	  for (int i = 0; i < 50; i++) {
	  // Replace p1 and p2 with constructors for AIs you want to test.
      OthelloPlayer p1 = new BattermanPlayer();
      //OthelloPlayer p2 = new ConstantTimePlayer();
      OthelloPlayer p2 = new BetterPlayer();
      OthelloObserver o = new OthelloTextObserver();
      
      //Uncomment this if you want to play against an AI as White.
      //OthelloPlayer o2 = new OthelloDisplay();
      OthelloGame g = new OthelloGame(p2, p1, o);
      g.run();
      if (((OthelloTextObserver)o).whiteWon()) {
    	  whiteWins++;
      } else {
    	  blackWins ++;
      }
      
	  }
	  
	  System.out.println("White wins " + whiteWins + " Black wins " + blackWins);
      //Uncomment this if you want to play against an AI as black.
      //OthelloPlayer o2 = new OthelloDisplay();
      //OthelloGame g = new OthelloGame(p1, p2, o);
      
      //Comment this if playing against an AI.
      //By default, we put two AIs against each other.
      //OthelloGame g = new OthelloGame(p1, p2, o);
      
      //runTests();
   }
   
   private static void runTests() {
	   /** Testing method. **/
	   long x = System.currentTimeMillis();
	   System.out.println(System.currentTimeMillis());
	   while (System.currentTimeMillis() - x < 1000) { }
	   System.out.println(System.currentTimeMillis());
	   
	   
	   Node testNode = new Node();
	   long whitePieces = Masks.bitAt[28] | Masks.bitAt[35];
	   long blackPieces = Masks.bitAt[27] | Masks.bitAt[36];
	   System.out.println(MoveGen.moveCount(whitePieces, blackPieces));
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
