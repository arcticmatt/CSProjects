import java.util.*;

public class TranspositionTable {
	/** Represents a transposition table **/
	
	private Bucket buckets[];
	
	public TranspositionTable(int n) {
		/* Creates a transition table, with n entries */
		buckets = new Bucket[n];
		for (int i = 0; i < n; i++) {
			 buckets[i] = new Bucket();
			 //System.out.println("cosntinuing..");
		}
		System.out.println("continuing..");
	}
	
	public Bucket getBucket(int hash) {
		/* Returns the bucket associated with the given hash value. */
		return buckets[hash];
	}
	
}
