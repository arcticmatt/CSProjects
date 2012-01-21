import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * An implementation of the gift wrapping algorithm.
 */
public class GiftWrapper extends ConvexHullAlgorithm {

  /**
   * Computes the convex hull of a set of points using the gift wrapping algorithm.
   * 
   * @param points The set of points whose convex hull is to be computed.
   * @return A List<Point> representing the convex hull of the input set.
   */
  public List<Point> convexHull(Set<Point> points) {
    List<Point> hull = new LinkedList<Point>();
    /* Start with the leftmost point */
    Point leftmostPoint = getLeftMostPoint(points);
    Point currentPoint = leftmostPoint;
    Point previousPoint = leftmostPoint;
    Point nextPoint;
    do {
    	/* Keep finding hull points and adding them to the hull */
    	nextPoint = findNextPoint(currentPoint, previousPoint, points);
    	previousPoint = currentPoint;
    	currentPoint = nextPoint;
    	hull.add(nextPoint);
	/* If the next point is the leftmost point, we've completed our 
	 * hull; exit the loop.
	 */
    } while (!nextPoint.equals(leftmostPoint));
    
    return hull;
  }

  /** Gets the next point; if you form a point from the currentPoint to that point, then
   * all other points will be on a single side of the line.
   */
  private Point findNextPoint(Point currentPoint, Point previousPoint, Set<Point> points) {
	  for (Point testPoint : points) {
		  	/* Run through all the points, and check if they're valid hull points */
		  	if (testPoint.equals(previousPoint) || testPoint.equals(currentPoint)) {
		  		//if the point we're testing is either of these two prohibited points, 
		  		//discard it.
		  		continue;
		  	}
		  	//temporary variables to store if we've found points on either side of the bound.
		  	Boolean foundLeft = false;
		  	Boolean foundRight = false;
		  	//Does this point work?
		  	Boolean isValid = true;
		  	//The line segment connecting the currentPoint to this point we're testing.
	  		LineSegment connector = new LineSegment(currentPoint, testPoint);
		  	/* Now loop through the other points and determine if they're all on one 
		  	 * side of the line.
		  	 */
		  	for (Point i : points) {
		  		if (i.equals(currentPoint) || i.equals(testPoint)) {
		  			/* if the point we're testing is either of the prohibited points,
		  			 * then continue.
		  			 */
		  			continue;
		  		}
		  		
		  		if (leftOfLine(i, connector)) {
		  			//we've found a point on the left
		  			foundLeft = true;
		  		} else {
		  			foundRight = true;
		  		}
		  		/* If we've found points on both side of the line, discard this test point. */
		  		if (foundLeft && foundRight) {
		  			isValid = false;
		  			break;
		  		}
		  	}
		  	if (isValid) {
		  		return testPoint;
		  	}
	  }
	  return null;
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

  /** Returns the left most point of the set **/
  private Point getLeftMostPoint(Set<Point> points) {

	  Point leftmostPoint = null;
	  for (Point i : points) {
		  if (leftmostPoint == null || i.getX() < leftmostPoint.getX()) {
			  leftmostPoint = i;
		  }
	  }
	  return leftmostPoint;
  }
  
}
