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
    //Copy the points into pointArray
    int i = 0;
    for (Point p : points) {
    	pointArray[i] = p;
    	i++;
    }
    Arrays.sort(pointArray);
    //place the lowest point into hull.
    hull.add(lowestPoint);
    /* Run through all the points, arranged by angles and add them to
     * the hull if they are convex; adjust the hull otherwise.
     */
    for (i = 0; i < pointArray.length; i++) {
    	Point testPoint = pointArray[i];
    	if (testPoint.equals(lowestPoint)) {
    		continue;
    	}
    	while (!isConvex(testPoint, hull)) {
    		//Remove until we find a point that makes it convex
    		hull.remove(hull.size() - 1);
    	}
    	hull.add(testPoint);
    	
    }
    
    return hull;
  }

  
  /** Returns whether the given point is convex, given the current hull. It is 
   *  convex if it is counterclockwise of the previous two points.**/
  private boolean isConvex(Point p, List<Point> hull) {
	  //The number of points currently in the hull
	  int hullSize = hull.size();
	  if (hullSize < 2) {
		  //Not enough points; must be convex.
		  return true;
	  }
	  Point lastPoint = hull.get(hullSize - 1);
	  Point secondLastPoint = hull.get(hullSize - 2);
	  LineSegment l1 = new LineSegment(lastPoint, p);
	  LineSegment l2 = new LineSegment(secondLastPoint, lastPoint);
	  double dTheta = getLineAngle(l1) - getLineAngle(l2);
	  if (normalizeAngle(dTheta) >= 0) {
		  //If it's counterclockwise, return true
		  return true;
	  }
	  return false;
  }
  
  private double normalizeAngle(double theta) {
	  while (theta > 2 * Math.PI) {
		  theta -= 2 * Math.PI;
	  }
	  while (theta < -2 * Math.PI){
		  theta += 2 * Math.PI;
	  }
	  return theta;
  }
  
  /** Returns the angle of the line segment with respect to the horizontal. 
   * Angles always > 0.
   * **/
  private double getLineAngle(LineSegment l) {
	  Point startPoint = l.getStart();
	  Point endPoint = l.getEnd();
	  double dx = endPoint.getX() - startPoint.getX();
	  double dy = endPoint.getY() - startPoint.getY();
	  double angle = Math.atan2(dy, dx);
	  if (angle < 0) {
		  angle = angle + 2*Math.PI;
	  }
	  return angle;
  }
  
  /** Gets the angles associated with each of the points, setting them in the
   * each of the points (using the associated data methods). Angles are 
   * relative to the lowest point.
   */
  private void calculateAngles(Point lowestPoint, Set<Point> points) {
	  for (Point p : points) {
		  //If the point is the lowestPoint, continue.
		  if (p.equals(lowestPoint)) {
			  p.setData(0);
			  continue;
		  }
		  LineSegment l = new LineSegment(lowestPoint, p);
		  /* Calculate the angle and associate it with the point */
		  double angle = getLineAngle(l);
		  p.setData(angle);
	  }
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
