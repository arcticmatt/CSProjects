/**
 * A class that tests a sorting implementation on the command line.
 * 
 * @author Justin Johnson
 */
public class TextualSortingApplication {

  private int arraySize;
  private double[] array;
  
  /**
   * Creates a new TextualSortingApplication which tests a sorting
   * implementation. An array of the specified size is generated and the
   * specified sorter is used to sort the array.
   * @param sorter The sorting implementation to use.
   * @param arraySize The size of the array to sort.
   */
  public TextualSortingApplication(BaseSorter sorter, int arraySize) {
    this.arraySize = arraySize;
    refreshArray();
    
    System.out.println("Trying to sort the array using a "
        + sorter.getClass().getName());
    sorter.sort(array);
    
    if (!sorter.isSorted(array)) {
      System.out.println("Ooops! The array wasn't sorted correctly.");
    } else {
      System.out.println("Hooray! The array was successfully sorted.");
    }
    
    System.out.println("The sorting algorithm used " + sorter.getNumSwaps()
        + " swaps and " + sorter.getNumCompares() + " comparisons.");
  }
  
  private void refreshArray() {
    System.out.println("Generating a new array of " + arraySize + " elements.");
    array = new double[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = 10.0 * (Math.random() - 0.5);
    }
  }
  
}
