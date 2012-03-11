import java.util.*;

public class Bucket {
	/** Represents a bucket; used in a TranspositionTable **/
	private LinkedList<Node> bucket = new LinkedList<Node>();
	public Bucket() {}
	public Node findBoard(Board b) {
		/** Tries to locate the given board -- returns Node it 
		 * belongs to if successful,
		 * null otherwise.
		 */
		for (Node i : bucket) {
			if (i.getBoard().equals(b)) {
				/* Found it */
				return i;
			}
		}
		/* if we don't find any, return null */
		return null;
	}
	
	public void addNode(Node node) {
		/** Adds a node to the bucket **/
		bucket.add(node);
	}
}
