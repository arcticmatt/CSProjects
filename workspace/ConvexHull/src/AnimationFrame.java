import java.util.HashSet;
import java.util.Set;

/**
 * An object which represents a frame of an animation for a convex hull
 * algorithm. A single frame may add or remove emphasis from points and also
 * draw or delete line segments.
 * 
 * @author Justin Johnson
 *
 */
public class AnimationFrame {

  private Set<Point> pointsToAdd, pointsToRemove;
  private Set<LineSegment> segmentsToAdd, segmentsToRemove;

  public AnimationFrame() {
    pointsToAdd = new HashSet<Point>();
    pointsToRemove = new HashSet<Point>();
    segmentsToAdd = new HashSet<LineSegment>();
    segmentsToRemove = new HashSet<LineSegment>();
  }

  /**
   * Emphasize the specified point in this frame. Null values are ignored.
   */
  public void addPoint(Point p) {
    if (p != null) {
      pointsToAdd.add(p);
    }
  }

  /**
   * Stop emphasizing the specified point in this frame. Null values are
   * ignored.
   */
  public void removePoint(Point p) {
    if (p != null) {
      pointsToRemove.add(p);
    }
  }

  /**
   * Draw the specified line segment in this frame. Null values are ignored.
   */
  public void addLineSegment(LineSegment segment) {
    if (segment != null) {
      segmentsToAdd.add(segment);
    }
  }

  /**
   * Delete the specified line segment in this frame. Null values are
   * ignored.
   */
  public void removeLineSegment(LineSegment segment) {
    if (segment != null) {
      segmentsToRemove.add(segment);
    }
  }

  /**
   * Finalizes this frame so that it can be scheduled for display.
   * In particular, The intersection of pointsToAdd and pointsToRemove are
   * removed from both pointsToAdd and pointsToRemove, and the same operation
   * is performed on segmentsToAdd and segmentsToRemove.
   * 
   * This means that any points or lines that have been both drawn and
   * deleted in the same frame will be ignored.
   */
  public void finalize() {
    Set<Point> pointsInBoth = new HashSet<Point>();
    for (Point p : pointsToAdd) {
      if (pointsToRemove.contains(p)) {
        pointsInBoth.add(p);
      }
    }
    for (Point p : pointsInBoth) {
      pointsToAdd.remove(p);
      pointsToRemove.remove(p);
    }

    Set<LineSegment> segmentsInBoth = new HashSet<LineSegment>();
    for (LineSegment seg : segmentsToAdd) {
      if (segmentsToRemove.contains(seg)) {
        segmentsInBoth.add(seg);
      }
    }
    for (LineSegment seg : segmentsInBoth) {
      segmentsToAdd.remove(seg);
      segmentsToRemove.remove(seg);
    }
  }

  /**
   * Get the set of points that will be drawn in this frame.
   */
  public Set<Point> getPointsToAdd() {
    return pointsToAdd;
  }

  /**
   * Get the set of points that will be deleted in this frame.
   */
  public Set<Point> getPointsToRemove() {
    return pointsToRemove;
  }

  /**
   * Get the set of line segments that will be newly drawn in this frame.
   */
  public Set<LineSegment> getSegmentsToAdd() {
    return segmentsToAdd;
  }

  /**
   * Get the set of line segments that will be deleted in this frame.
   */
  public Set<LineSegment> getSegmentsToRemove() {
    return segmentsToRemove;
  }

}
