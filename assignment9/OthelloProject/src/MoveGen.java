import java.util.*;

public class MoveGen {
	
	public static void generateChildren(Node n) {
		/** Generates the children of the given node, setting
		 * them appropriately.
		 **/
		
		/* moverPieces are the pieces of the side that is moving; idlerPieces
		 * those of the side which is not moving.
		 */
		long moverPieces, idlerPieces;
		/* first get the board, check which side is moving, and
		 * generate moves accordingly.
		 */
		Board board = n.getBoard();
		long blackPieces = board.getBlackPieces();
		long whitePieces = board.getWhitePieces();
		byte movingSide = board.getMovingSide();
		byte idleSide = board.getIdleSide();
		/* The board generation function is used when creating the children to avoid conditionals */
		BoardGenerationFunction bgf ;
		if (movingSide == Constants.WHITE) {
			moverPieces = whitePieces;
			idlerPieces = blackPieces;
			/* UGLY!!!! */
			bgf = new BoardGenerationFunction() {
				@Override
				public Board childBoard(long whitePieces, long blackPieces,
						long nextMoveBoard, long killedSquares, byte idleSide) {
					return new Board((whitePieces ^ killedSquares) | nextMoveBoard, blackPieces ^ killedSquares, idleSide);
				}
			};
			
		} else {
			moverPieces = blackPieces;
			idlerPieces = whitePieces;
			bgf = new BoardGenerationFunction() {
				@Override
				public Board childBoard(long whitePieces, long blackPieces,
						long nextMoveBoard, long killedSquares, byte idleSide) {
					return new Board(whitePieces ^ killedSquares, (blackPieces ^ killedSquares) | nextMoveBoard, idleSide);
				}
			};
		}
		
		/* The list of Nodes containing all the children */
		LinkedList<Node> children = new LinkedList<Node>();
		
		/* freeSquares represents all the squares where no pieces are located. */
		long freeSquares = ~(moverPieces | idlerPieces);
		/* Place all the squares where the attacker can move to in one bitboard. */
		long movableSquares = 0L;
		/* get all the attacked squares in all the directions & update movableSquares accordingly.*/
		long attackedSquaresN = getAttackedPiecesUP(moverPieces, idlerPieces, Constants.N, Masks.allFiles);
		movableSquares |= getMovableSquaresUP(attackedSquaresN, Constants.N, Masks.allFiles, freeSquares);
		
		long attackedSquaresNE = getAttackedPiecesUP(moverPieces, idlerPieces, Constants.NE, Masks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNE, Constants.NE, Masks.notHFile, freeSquares);
		
		long attackedSquaresE = getAttackedPiecesUP(moverPieces, idlerPieces, Constants.E, Masks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresE, Constants.E, Masks.notHFile, freeSquares);
		
		long attackedSquaresSE = getAttackedPiecesDOWN(moverPieces, idlerPieces, Constants.SE, Masks.notHFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSE, Constants.SE, Masks.notHFile, freeSquares);
		
		long attackedSquaresS = getAttackedPiecesDOWN(moverPieces, idlerPieces, Constants.S, Masks.allFiles);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresS, Constants.S, Masks.allFiles, freeSquares);
		
		long attackedSquaresSW = getAttackedPiecesDOWN(moverPieces, idlerPieces, Constants.SW, Masks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSW, Constants.SW, Masks.notAFile, freeSquares);
		
		long attackedSquaresW = getAttackedPiecesDOWN(moverPieces, idlerPieces, Constants.W, Masks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresW, Constants.W, Masks.notAFile, freeSquares);
		
		long attackedSquaresNW = getAttackedPiecesUP(moverPieces, idlerPieces, Constants.NW, Masks.notAFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNW, Constants.NW, Masks.notAFile, freeSquares);
		
		/* foundChildren stores whether any possible moves were found; if none were found, add the /pass/ move
		 * to the list.
		 */
		boolean foundChildren = false;
		while (movableSquares != 0L) {
			foundChildren = true;
			/* Get the index of the next square we can move to. */
			int nextMoveIndex = BSF(movableSquares);
			/* Get a board consisting of only that piece, and remove it from movableSquares */
			long nextMoveBoard = Masks.bitAt[nextMoveIndex];
			movableSquares ^= nextMoveBoard;
			/* killedSquares contains all the squares which need to be removed upon placing a piece at the 
			 * nextMoveIndex location.
			 */
			long killedSquares = 0L;
			/* Go through all directions, getting all the squares which can be killed by placing on this square */
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresN, Constants.S, Masks.allFiles);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresNE, Constants.SW, Masks.notAFile);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresE, Constants.W, Masks.notAFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresSE, Constants.NW, Masks.notAFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresS, Constants.N, Masks.allFiles);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresSW, Constants.NE, Masks.notHFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresW, Constants.E, Masks.notHFile);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresNW, Constants.SE, Masks.notHFile);
			Node child = new Node();
			/* Set the parent & board appropriately */
			child.setParent(n);
			Board childBoard = bgf.childBoard(whitePieces, blackPieces, nextMoveBoard, killedSquares, idleSide);
			child.setBoard(childBoard);
			/* tell the child which move created it */
			child.setParentMove(nextMoveIndex);
			children.add(child);
		}
		/* if we didn't find any children, add the /pass/ move */
		if (!foundChildren) {
			Node child = new Node();
			child.setParent(n);
			/* Just change the moving side. */
			Board childBoard = new Board(whitePieces, blackPieces, idleSide);
			child.setCreatedByPass(true);
			child.setBoard(childBoard);
			children.add(child);
		}
		/* finally, after we get generate the children, set the original node's children appropriately. */
		n.setChildren(children);
	}
	
	interface BoardGenerationFunction {
		Board childBoard(long whitePieces, long blackPieces, long nextMoveBoard, long killedSquares, byte idleSide);
	}
	
	private static long getMovableSquaresUP(long attackedSquares, int delta, long mask, long free) {
		/** Returns the squares where an attacker can move to, given the attackedSquares board.
		 *  Bits are shifted to the LEFT. **/
		return ((attackedSquares & mask) << delta) & free;
	}
	
	private static long getMovableSquaresDOWN(long attackedSquares, int delta, long mask, long free) {
		/** Returns the squares where an attacker can move to, given the attackedSquares board.
		 *  Bits are shifted to the RIGHT. **/
		return ((attackedSquares & mask) >>> delta) & free;
	}
	
	private static long getAttackedPiecesUP(long moverPieces, long idlerPieces, int delta, long mask) {
		/** Generates all the squares attacked by the mover (i.e. all squares of
		 * the idler which can be killed) by placing pieces in the +delta direction.
		 * Mask is used to prevent pieces from reaching illegitimate positions (i.e. A-file upon
		 * a left shift). Bits are shifted to the LEFT.
		**/
		long moves = moverPieces;
		/* We won't put it in a for-loop to avoid branching */
		moves = ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		moves |= ((moves & mask) << delta) & idlerPieces;
		return moves;
	}
	
	private static long getAttackedPiecesDOWN(long moverPieces, long idlerPieces, int delta, long mask) {
		/** Generates all the squares attacked by the mover (i.e. all squares of
		 * the idler which can be killed) by placing pieces in the +delta direction.
		 * Mask is used to prevent pieces from reaching illegitimate positions (i.e. A-file upon
		 * a left shift). Bits are shifted to the RIGHT.
		**/
		long moves = moverPieces;
		/* We won't put it in a for-loop to avoid branching */
		moves = ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		moves |= ((moves & mask) >>> delta) & idlerPieces;
		return moves;
	}
	
	private static int BSF(long x) {
		/** Returns the index of the first set bit in x. Assumes x > 0 **/
		return Long.bitCount(x ^ (x-1)) - 1;
	}
	
	public static void printBitboard(long x) {
		/** Prints the given board in a human-readable format. Useful for debugging. **/
		System.out.println();
		for (int i = 7; i >= 0; i--) {
			String output = "";
			for (int j = 0; j < 8; j++) {
				if ((Masks.bitAt[i * 8 + j] & x) != 0L) {
					output += "1";
				} else {
					output += "0";
				}
			}
			System.out.println(output);
		}
		System.out.println();
	}
	
}
