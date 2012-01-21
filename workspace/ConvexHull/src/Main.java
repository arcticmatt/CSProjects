
/**
 * Contains the entry point for the convex hull application.
 * 
 * @author Justin Johnson
 */
public class Main {

  private static final int NUM_POINTS = 100;

  /**
   * Entry point to the convex hull application.
   * @param args
   */
  public static void main(String[] args) {
    ConvexHullAlgorithm algorithm = new GiftWrapper();
    
    new ConvexHullApplication(algorithm, NUM_POINTS);
  }
  
}
