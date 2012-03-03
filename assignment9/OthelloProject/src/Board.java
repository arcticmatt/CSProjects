

public class Board {
	
	private long whitePieces, blackPieces;
	private byte movingSide;
	public Board(long whitePieces, long blackPieces, byte movingSide) {
		/* Initialize a board with the given whitePieces / blackPieces. */
		this.whitePieces = whitePieces;
		this.blackPieces = blackPieces;
		this.movingSide = movingSide;
	}
	
	public long getWhitePieces() {
		/* Returns the white pieces on this board. */
		return whitePieces;
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
	
	public boolean equals(Board b) {
		/* Returns whether this board equals the other board */
		return (getWhitePieces() == b.getWhitePieces() && 
				getBlackPieces() == b.getBlackPieces() &&
				getMovingSide() == b.getMovingSide());
	}
	
}
