public class TestGame
{
   public static void main(String[] args)
   {

	  // Replace p1 and p2 with constructors for AIs you want to test.
      OthelloPlayer p1 = new BattermanPlayer();
      //OthelloPlayer p2 = new ConstantTimePlayer();
      OthelloObserver o = new OthelloDisplay();

      //Uncomment this if you want to play against an AI as White.
      OthelloPlayer o2 = new OthelloDisplay();
      OthelloGame g = new OthelloGame(p1, o2, o);
      
      //Uncomment this if you want to play against an AI as black.
      //OthelloPlayer o2 = new OthelloDisplay();
      //OthelloGame g = new OthelloGame(o2, p1, o);
      
      //Comment this if playing against an AI.
      //By default, we put two AIs against each other.
      //OthelloGame g = new OthelloGame(p1, p2, o);
      //Ulong j;
 
      runTests();
      
      //g.run();
   }
   
   private static void runTests() {
	   /** Testing method. **/
	   Node testNode = new Node();
	   long blackPieces = Masks.bitAt[28] | Masks.bitAt[35];
	   long whitePieces = Masks.bitAt[27] | Masks.bitAt[36];
	   Board b = new Board(whitePieces, blackPieces, Constants.WHITE);
	   testNode.setBoard(b);
	   System.out.println("starting");
	   //for (int i = 0; i < 5000000; i++) {
	   MoveGen.generateChildren(testNode);
	   //testNode.setChildren(null);
	   //}
	   
	  // System.out.println(testNode.getChildren().size());
	   //boardTestboard = testNode.getChildren().peek().getBoard();
	   MoveGen.printBitboard(testNode.getChildren().get(0).getBoard().getWhitePieces());
	   MoveGen.printBitboard(testNode.getChildren().get(0).getBoard().getBlackPieces());
	   System.out.println("ending");
	   
	   StringFunction x = new StringFunction() {
		
		@Override
		public int function(String param) {
			// TODO Auto-generated method stub
			return 5;
		}
	};
	  
	System.out.println(x.function(""));
	
   }
   
   interface StringFunction {
	    int function(String param);
	}
   
}
