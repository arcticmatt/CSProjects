package missilecommand;

/** This class implements a message in the missile command **/

public class Message {
	private int X, Y, cycles;
	private String text;
	
	public Message(String text, int X, int Y, int cycles) {
		/* Initiates a message */
		this.X = X;
		this.Y = Y;
		this.text = text;
		/* Cycles represents the number of cycles during which the
		 * message is active.
		 */
		this.cycles = cycles;
	}
	
	/** Returns the length of the message **/
	public int getLength() {
		return text.length();
	}
	
	/** Returns the given message as a char array **/
	public char[] chars() {
		return text.toCharArray();
	}
	
	public int getX() {
		return X;
	}
	public int getY() { 
		return Y;
	}
	
	public boolean isDead() {
		return (cycles <= 0);
	}

	public void decrementCycles() {
		cycles--;
	}
	
}
