
/**
 * This class contains the main method for the sorting application. 
 * 
 * @author Justin Johnson
 */
public class Main {
  
  // The size of the array to sort.
  //private static final int ARRAY_SIZE = 50;

  /**
   * The main entry point of the sorting application.
   */
  public static void main(String[] args) throws InterruptedException {
    // Change this line to use a different sorting algorithm!
  //  BaseSorter sorter = new MergeSorter();
   // new TextualSortingApplication(sorter, ARRAY_SIZE);
	//  int[] bsortData = new int[100];
	//  int[] sSortData = new int[100];
	 // int[] mSortData = new int[100];
	  String sSort = "";
	  String bSort = "";
	  String mSort = "";
	  for (int i = 0; i < 500; i++) {
		  bSort += bubbleSortCompares(i) + ",";
		  sSort += selectionSortCompares(i) + ",";
		  mSort += mergeSortCompares(i) + ",";
		  System.out.println(mergeSortCompares(i));
	  }
	  System.out.println(bSort);
	  System.out.println(sSort);
	  System.out.println(mSort);
  }
  
  private static int bubbleSortCompares(int arraySize) {
	  	BaseSorter sorter = new BubbleSorter();
	    new TextualSortingApplication(sorter, arraySize);
	    return sorter.getNumCompares();
  }
  private static int selectionSortCompares(int arraySize) {
	  	BaseSorter sorter = new SelectionSorter();
	    new TextualSortingApplication(sorter, arraySize);
	    return sorter.getNumCompares();
  }
  
  private static int mergeSortCompares(int arraySize) {
	  	BaseSorter sorter = new MergeSorter();
	    new TextualSortingApplication(sorter, arraySize);
	    return sorter.getNumCompares();
  }
  
  
}
