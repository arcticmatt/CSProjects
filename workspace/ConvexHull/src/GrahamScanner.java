import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * An implementation of the Graham scan algorithm.
 */
public class GrahamScanner extends ConvexHullAlgorithm {

  /**
   * Computes the convex hull of a set of points using the Graham scan
   * algorithm.
   */
  public List<Point> convexHull(Set<Point> points) {
    List<Point> hull = new LinkedList<Point>();
    //Get the lowest point
    Point lowestPoint = getLowestPoint(points);
    //Calculate the relative angles
    calculateAngles(lowestPoint, points);
    //Place them in an array and sort them according to their angles
    Point[] pointArray = new Point[points.size()];
    Arrays.sort(pointArray);
    
    return hull;
  }

  
  
  /** Gets the angles associated with each of the points, setting them in the
   * each of the points (using the associated data methods). Angles are 
   * relative to the lowest point.
   */
  private void calculateAngles(Point lowestPoint, Set<Point> points) {
	  for (Point p : points) {
		  //If the point is the lowestPoint, continue.
		  if (p.equals(lowestPoint)) {
			  continue;
		  }
		  /* Calculate the angle and associate it with the point */
		  double dx = p.getX() - lowestPoint.getX();
		  double dy = p.getY() - lowestPoint.getY();
		  double angle = Math.atan(dy / dx);
		  p.setData(angle);
	  }
  }
  
  /** Checks if the given point is on the left side of the
   * line segment.
   */
  private Boolean leftOfLine(Point p, LineSegment l) {
	  double x0, x1, y0, y1, x, y;
	  Point startPoint = l.getStart();
	  Point endPoint = l.getEnd();
	  x0 = startPoint.getX();
	  y0 = startPoint.getY();
	  x1 = endPoint.getX();
	  y1 = endPoint.getY();
	  x = p.getX();
	  y = p.getY();
	  /* We'll evaluate (y1 - y0)*(x - x0) + (x1 - x0)*(y0 - y) and get its
	   * relation to 0 to determine which side of the line p is on; if it
	   * evaluates to less than 0, then the point is indeed on the left side.
	   */
	  return  (y1 - y0) * (x - x0) + (y0 - y)*(x1 - x0) < 0;
  }
  
  /** Returns the lowest point in the set; the one with the lowest y value **/
  private Point getLowestPoint(Set<Point> points) {
	  Point lowestPoint = null;
	  for (Point p : points) {
		  if (lowestPoint == null || p.getY() < lowestPoint.getY()) {
			  lowestPoint = p;
		  }
	  }
	  return lowestPoint;
  }
  
}
