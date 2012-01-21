import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * This subclass of ConvexHullAlgorithm simply demonstrates how to implement
 * animations; it DOES NOT correctly compute convex hulls.
 * 
 * @author Justin Johnson
 *
 */
public class AnimationExample extends ConvexHullAlgorithm {

  /**
   * An example which demonstrates how to implement animation. Note that it
   * DOES NOT correctly compute the convex hull of the input set!
   */
  public List<Point> convexHull(Set<Point> points) {
    List<Point> hull = new LinkedList<Point>();

    Point lastPoint = null;
    LineSegment lastSegment = null;
    for (Point p : points) {
      if (lastPoint != null) {
        LineSegment seg = new LineSegment(lastPoint, p);

        // Schedules this line segment for display.
        drawSegment(seg);

        if (lastSegment != null) {
          // Schedules this line segment for deletion.
          deleteSegment(lastSegment);
        }

        // Actually draws and deletes the two segments above.
        refreshDisplay();

        lastSegment = seg;

        hull.add(p);
      }
      lastPoint = p;
    }

    return hull;
  }

}
