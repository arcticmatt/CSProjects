import java.util.*;

public class BattermanMoveGen {
	
	
	public static void generateChildren(BattermanNode n) {
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
		BattermanBoard board = n.getBoard();
		long blackPieces = board.getBlackPieces();
		long whitePieces = board.getWhitePieces();
		byte movingSide = board.getMovingSide();
		byte idleSide = board.getIdleSide();
		/* The board generation function is used when creating the children to avoid conditionals */
		BoardGenerationFunction bgf ;
		if (movingSide == BattermanConstants.WHITE) {
			moverPieces = whitePieces;
			idlerPieces = blackPieces;
			/* UGLY!!!! */
			bgf = new BoardGenerationFunction() {
				@Override
				public BattermanBoard childBoard(long whitePieces, long blackPieces,
						long nextMoveBoard, long killedSquares, byte idleSide) {
					return new BattermanBoard((whitePieces ^ killedSquares) | nextMoveBoard, blackPieces ^ killedSquares, idleSide);
				}
			};
			
		} else {
			moverPieces = blackPieces;
			idlerPieces = whitePieces;
			bgf = new BoardGenerationFunction() {
				@Override
				public BattermanBoard childBoard(long whitePieces, long blackPieces,
						long nextMoveBoard, long killedSquares, byte idleSide) {
					return new BattermanBoard(whitePieces ^ killedSquares, (blackPieces ^ killedSquares) | nextMoveBoard, idleSide);
				}
			};
		}
		
		/* The list of Nodes containing all the children */
		LinkedList<BattermanNode> children = new LinkedList<BattermanNode>();
		
		/* freeSquares represents all the squares where no pieces are located. */
		long freeSquares = ~(moverPieces | idlerPieces);
		/* Place all the squares where the attacker can move to in one bitboard. */
		long movableSquares = 0L;
		/* get all the attacked squares in all the directions & update movableSquares accordingly.*/
		long attackedSquaresN = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.N, BattermanMasks.allFiles);
		movableSquares |= getMovableSquaresUP(attackedSquaresN, BattermanConstants.N, BattermanMasks.allFiles, freeSquares);
		
		long attackedSquaresNE = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.NE, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNE, BattermanConstants.NE, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresE = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.E, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresE, BattermanConstants.E, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresSE = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.SE, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSE, BattermanConstants.SE, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresS = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.S, BattermanMasks.allFiles);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresS, BattermanConstants.S, BattermanMasks.allFiles, freeSquares);
		
		long attackedSquaresSW = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.SW, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSW, BattermanConstants.SW, BattermanMasks.notAFile, freeSquares);
		
		long attackedSquaresW = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.W, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresW, BattermanConstants.W, BattermanMasks.notAFile, freeSquares);
		
		long attackedSquaresNW = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.NW, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNW, BattermanConstants.NW, BattermanMasks.notAFile, freeSquares);
		
		/* foundChildren stores whether any possible moves were found; if none were found, add the /pass/ move
		 * to the list.
		 */
		boolean foundChildren = false;
		while (movableSquares != 0L) {
			foundChildren = true;
			/* Get the index of the next square we can move to. */
			int nextMoveIndex = BSF(movableSquares);
			/* Get a board consisting of only that piece, and remove it from movableSquares */
			long nextMoveBoard = BattermanMasks.bitAt[nextMoveIndex];
			movableSquares ^= nextMoveBoard;
			/* killedSquares contains all the squares which need to be removed upon placing a piece at the 
			 * nextMoveIndex location.
			 */
			long killedSquares = 0L;
			/* Go through all directions, getting all the squares which can be killed by placing on this square */
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresN, BattermanConstants.S, BattermanMasks.allFiles);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresNE, BattermanConstants.SW, BattermanMasks.notAFile);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresE, BattermanConstants.W, BattermanMasks.notAFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresSE, BattermanConstants.NW, BattermanMasks.notAFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresS, BattermanConstants.N, BattermanMasks.allFiles);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresSW, BattermanConstants.NE, BattermanMasks.notHFile);
			killedSquares |= getAttackedPiecesUP(nextMoveBoard, attackedSquaresW, BattermanConstants.E, BattermanMasks.notHFile);
			killedSquares |= getAttackedPiecesDOWN(nextMoveBoard, attackedSquaresNW, BattermanConstants.SE, BattermanMasks.notHFile);
			BattermanNode child = new BattermanNode();
			/* Set the parent & board appropriately */
			child.setParent(n);
			BattermanBoard childBoard = bgf.childBoard(whitePieces, blackPieces, nextMoveBoard, killedSquares, idleSide);
			child.setBoard(childBoard);
			/* tell the child which move created it */
			child.setParentMove(nextMoveIndex);
			children.add(child);
		}
		/* if we didn't find any children, add the /pass/ move */
		if (!foundChildren) {
			BattermanNode child = new BattermanNode();
			child.setParent(n);
			/* Just change the moving side. */
			BattermanBoard childBoard = new BattermanBoard(whitePieces, blackPieces, idleSide);
			child.setCreatedByPass(true);
			child.setBoard(childBoard);
			children.add(child);
		}
		/* finally, after we get generate the children, set the original node's children appropriately. */
		n.setChildren(children);
	}
	
	public static int frontierSquaresCount(long moverPieces, long idlerPieces) {
		long freeSquares = ~(moverPieces | idlerPieces);
		long frontierSquares = 0L;
		long notAHFiles = BattermanMasks.notAFile & BattermanMasks.notHFile;
		long notFirstLastRanks = BattermanMasks.notFirstRank & BattermanMasks.notLastRank;
		frontierSquares |= (moverPieces & notFirstLastRanks) << BattermanConstants.N;
		frontierSquares |= (moverPieces & notAHFiles & notFirstLastRanks) << BattermanConstants.NE;
		frontierSquares |= (moverPieces & notAHFiles) << BattermanConstants.E;
		frontierSquares |= (moverPieces & notAHFiles & notFirstLastRanks) >>> BattermanConstants.SE;
		frontierSquares |= (moverPieces & notFirstLastRanks) >>> BattermanConstants.S;
		frontierSquares |= (moverPieces & notFirstLastRanks & notAHFiles) >>> BattermanConstants.SW;
		frontierSquares |= (moverPieces & notAHFiles) >>> BattermanConstants.W;
		frontierSquares |= (moverPieces & notAHFiles & notFirstLastRanks) << BattermanConstants.NW;
		return Long.bitCount(frontierSquares & freeSquares);
	}
	
	public static int moveCount(long moverPieces, long idlerPieces) {
		/* Returns the number of moves for the moverPieces */
		/* freeSquares represents all the squares where no pieces are located. */
		long freeSquares = ~(moverPieces | idlerPieces);
		/* Place all the squares where the attacker can move to in one bitboard. */
		long movableSquares = 0L;
		/* get all the attacked squares in all the directions & update movableSquares accordingly.*/
		long attackedSquaresN = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.N, BattermanMasks.allFiles);
		movableSquares |= getMovableSquaresUP(attackedSquaresN, BattermanConstants.N, BattermanMasks.allFiles, freeSquares);
		
		long attackedSquaresNE = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.NE, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNE, BattermanConstants.NE, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresE = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.E, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresE, BattermanConstants.E, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresSE = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.SE, BattermanMasks.notHFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSE, BattermanConstants.SE, BattermanMasks.notHFile, freeSquares);
		
		long attackedSquaresS = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.S, BattermanMasks.allFiles);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresS, BattermanConstants.S, BattermanMasks.allFiles, freeSquares);
		
		long attackedSquaresSW = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.SW, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresSW, BattermanConstants.SW, BattermanMasks.notAFile, freeSquares);
		
		long attackedSquaresW = getAttackedPiecesDOWN(moverPieces, idlerPieces, BattermanConstants.W, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresDOWN(attackedSquaresW, BattermanConstants.W, BattermanMasks.notAFile, freeSquares);
		
		long attackedSquaresNW = getAttackedPiecesUP(moverPieces, idlerPieces, BattermanConstants.NW, BattermanMasks.notAFile);
		movableSquares |= getMovableSquaresUP(attackedSquaresNW, BattermanConstants.NW, BattermanMasks.notAFile, freeSquares);
		return Long.bitCount(movableSquares);
	}
	
	interface BoardGenerationFunction {
		BattermanBoard childBoard(long whitePieces, long blackPieces, long nextMoveBoard, long killedSquares, byte idleSide);
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
				if ((BattermanMasks.bitAt[i * 8 + j] & x) != 0L) {
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
