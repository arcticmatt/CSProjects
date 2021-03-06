/**
 * A BubbleSorter sorts arrays using the bubble sort algorithm.
 * 
 * @author Justin Johnson
 */
public class BubbleSorter extends BaseSorter {

  /**
   * Sorts an array using the bubble sort algorithm.
   * 
   * @param array The array to sort.
   */
  public void doSort(double[] array) {
	  
    for (int i = 0; i < array.length; i++) {
      for (int j = 1; j < array.length - i; j++) {
        if (lessThan(array, j, j - 1)) {
          swap(array, j, j - 1);
        }
      }
    }
    
    
  }
 

}
