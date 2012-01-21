import java.util.LinkedList;
import java.util.Queue;

/**
 * Base implementation of a sorter that tracks comparisons and swaps. Subclasses should override
 * the doSort method. To swap two elements subclasses should use the swap() method and to compare
 * elements subclases should use the lessThan() and equal() methods; in particular subclasses
 * SHOULD NOT compare array elements using native comparison operations.
 * 
 * @author Justin
 *
 */
public abstract class BaseSorter {

  private int numCompares = 0;
  private int numSwaps = 0;
  private Queue<Operation> ops = new LinkedList<Operation>();

  /**
   * Sort an array while tracking the comparisons and swaps for visualization.
   * 
   * @param array The array to sort.
   */
  public final void sort(double[] array) {
    numCompares = 0;
    numSwaps = 0;
    ops = new LinkedList<Operation>();
    doSort(array);
  }

  /**
   * Subclasses should override this method to actually sort the array.
   * 
   * @param array The array to sort.
   */
  protected abstract void doSort(double[] array);

  /**
   * Subclasses should use this method to swap two array elements. The elements
   * at index1 and index2 of array are swapped.
   * 
   * @param array The array whose elements are to be swapped.
   */
  protected final void swap(double[] array, int index1, int index2) {
    checkBounds(array, index1, index2);

    double temp = array[index1];
    array[index1] = array[index2];
    array[index2] = temp;
    numSwaps++;
    ops.add(new Operation(index1, index2, Operation.Type.SWAP));
  }

  /**
   * Private helper method to check index bounds.
   */
  private void checkBounds(double[] array, int index1, int index2) {
    if (index1 < 0 || index1 >= array.length) {
      throw new IndexOutOfBoundsException("index1 should be in the range 0 <= index1 < "
          + array.length +" but index1 = " + index1);
    }
    if (index2 < 0 || index2 >= array.length) {
      throw new IndexOutOfBoundsException("index2 should be in the range 0 <= index2 < "
          + array.length +" but index2 = " + index2);
    }
  }

  /**
   * Private helper method to implement comparisons.
   */
  private int compare(double[] array, int index1, int index2) {
    checkBounds(array, index1, index2);

    numCompares++;
    ops.add(new Operation(index1, index2, Operation.Type.COMPARE));

    double a = array[index1];
    double b = array[index2];

    if (a < b) {
      return -1;
    } else if (a > b) {
      return 1;
    } else {
      return 0;
    }
  }

  /**
   * Subclasses should use this method to compare array elements.
   * 
   * @param array The array whose elements are to be compared.
   * @return True iff array[index1] < array[index2].
   */
  protected final boolean lessThan(double[] array, int index1, int index2) {
    return compare(array, index1, index2) == -1;
  }

  /**
   * Subclasses should use this method to compare array elements.
   * 
   * @param array The array whose elements are to be compared.
   * @return True iff array[index1] == array[index2].
   */
  protected final boolean equal(double[] array, int index1, int index2) {
    return compare(array, index1, index2) == 0;
  }

  /**
   * Utility method to check whether an array is sorted.
   * 
   * @param array The array to check.
   * @return True iff the elements of array are in ascending order.
   */
  public final boolean isSorted(double[] array) {
    for (int i = 1; i < array.length; i++) {
      if (array[i - 1] > array[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets the Operations that have been performed.
   */
  public final Queue<Operation> getOperations() {
    Queue<Operation> ret = new LinkedList<Operation>();
    for (Operation op : ops) {
      ret.add(op);
    }
    return ret;
  }
  
  /**
   * Gets the number of swaps that have been performed.
   */
  public final int getNumSwaps() {
    return numSwaps;
  }
  
  /**
   * Gets the number of swaps that have been performed.
   */
  public final int getNumCompares() {
    return numCompares;
  }

}
