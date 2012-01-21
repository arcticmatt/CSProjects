/**
 * Represents an array operation, either a swap or a compare.
 * 
 * @author Justin
 */
public class Operation {

  public enum Type {
    SWAP, COMPARE;
  }

  private int index1, index2;
  private Type type;

  public Operation(int index1, int index2, Type type) {
    this.index1 = index1;
    this.index2 = index2;
    this.type = type;
  }

  public int getIndex1() {
    return index1;
  }

  public int getIndex2() {
    return index2;
  }

  public Type getType() { 
    return type;
  }

}
