
public class Constants {
	public static final byte WHITE = 0;
	public static final byte BLACK = 1;
	/* directions. Maybe put these in an enum?
	 */
	public static final byte NW = 7;
	public static final byte N = 8;
	public static final byte NE = 9;
	public static final byte W = 1;
	public static final byte E = 1;
	public static final byte SE = 7;
	public static final byte S = 8;
	public static final byte SW = 9;
	
	public static final int transpositionTableSize = 16777216; //2^24
	public static final int INF = 999999999;
}
