
/**
 * This class contains the main method for the sorting application. 
 * 
 * @author Justin Johnson
 */
public class Main {
  
  // The size of the array to sort.
  private static final int ARRAY_SIZE = 50;

  /**
   * The main entry point of the sorting application.
   */
  public static void main(String[] args) throws InterruptedException {
    // Change this line to use a different sorting algorithm!
    BaseSorter sorter = new MergeSorter();
    
    // Change the number to change the size of the array to sort.
    // Use a TextualSortingApplication if the sorting implementation modifies
    // on the array directly instead of using the swap method.
    new TextualSortingApplication(sorter, ARRAY_SIZE);
  //  new GraphicalSortingApplication(sorter, ARRAY_SIZE);
  }
  
}
