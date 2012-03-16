

public class BattermanBoard {
	
	private long whitePieces, blackPieces;
	private byte movingSide;
	public BattermanBoard(long whitePieces, long blackPieces, byte movingSide) {
		/* Initialize a board with the given whitePieces / blackPieces. */
		this.whitePieces = whitePieces;
		this.blackPieces = blackPieces;
		this.movingSide = movingSide;
	}
	
	public long getWhitePieces() {
		/* Returns the white pieces on this board. */
		return whitePieces;
	}
	
	public int getHash() {
		/** Returns the 3 byte hash of the board **/
		long allPieces = ((getBlackPieces() >>> 60) | (getBlackPieces() << 4)) ^ getWhitePieces();
		int hash = (int)movingSide << 6;
		hash ^= allPieces ^ (allPieces >>> 24) ^ (allPieces >>> 48);
		return (hash << 8) >>> 8;
	}
	
	public int getPieceCount() {
		/* Returns the number of pieces on the board */
		return Long.bitCount(getWhitePieces() | getBlackPieces());
	}
	
	public long getBlackPieces() {
		/* Returns the black pieces on the board */
		return blackPieces;
	}
	
	public byte getMovingSide() {
		return movingSide;
	}
	
	
	public byte getIdleSide() {
		/* returns the side which will not be moving */
		return (byte)(movingSide ^ 1);
	}
	
	public void setMovingSide(byte newMovingSide) {
		/* Set the moving side to a new value */
		movingSide = newMovingSide;
	}
	
	public boolean equals(BattermanBoard b) {
		/* Returns whether this board equals the other board */
		return (getWhitePieces() == b.getWhitePieces() && 
				getBlackPieces() == b.getBlackPieces() &&
				getMovingSide() == b.getMovingSide());
	}
	
}
