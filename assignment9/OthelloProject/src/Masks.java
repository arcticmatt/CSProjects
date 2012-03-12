
public class Masks {
	/* bitAt[x] contains a long with the xth bit set (0 indexed) */
	public static long[] bitAt = generateBitAt();
	public static long notAFile = generatenotAFile();
	public static long notHFile = generatenotHFile();
	public static long allFiles = ~(0L);
	public static long corners = bitAt[0] | bitAt[7] | bitAt[56] | bitAt[63];
	public static long notFirstRank = generateNotFirstRank();
	public static long notLastRank = generateNotLastRank();
	
	private static long generateNotFirstRank() {
		/** Generates the notFirstRank mask **/
		long output = 0L;
		for (int i = 0; i < 64; i++) {
			if (i / 8 != 0) {
				output |= bitAt[i];
			}
		}
		
		return output;
	}
	
	private static long generateNotLastRank() {
		/** Generates the notLastRank mask **/
		long output = 0L;
		for (int i = 0; i < 64; i++) {
			if (i / 8 != 7) {
				output |= bitAt[i];
			}
		}
		return output;
	}
	
	private static long[] generateBitAt() {
		/** Generates the bitAt mask **/
		long[] bitat = new long[64];
		for (int i = 0; i < 64; i++) {
			bitat[i] = (1L << i);
		}
		return bitat;
	}
	
	private static long generatenotAFile() {
		/** Generates the notAfile mask **/
		long output = 0L;
		for (int i = 0; i < 64; i++) {
			if ((i % 8) != 0) {
				output |= bitAt[i];
			}
		}
		
		return output;
	}
	
	private static long generatenotHFile() {
		long output = 0L;
		for (int i = 0; i < 64; i++) {
			if ((i % 8) != 7) {
				output |= bitAt[i];
			}
		}
		return output;
	}
	
}
