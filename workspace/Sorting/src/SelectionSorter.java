
/**
 * A SelectionSorter sorts arrays using the selection sort algorithm.
 */
public class SelectionSorter extends BaseSorter {

  /**
   * Sorts an array using the selection sort algorithm.
   * 
   * @param array
   */
  protected void doSort(double[] array) {
	  for (int i = 0; i < array.length; i++) {
		  /* Run through each index in the array, finding the minimum
		   * element with index > i, and swapping that into i
		   */
		  int minIndex = i;
		  for (int j = i+1; j < array.length; j++) {
			  if (lessThan(array, j, minIndex)) {
				  //if the jth entry < minIndex's entry
				  minIndex = j;
			  }
		  }
		  swap(array, i, minIndex);
	  }
  }

}
