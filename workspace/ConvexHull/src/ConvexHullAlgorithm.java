import java.util.List;
import java.util.Set;

/**
 * To implement different convex hull algorithms, this class should be extended
 * and the convesHull() method should be implemented. Subclasses may add
 * animations by using the drawPoint(), deletePoint(), drawSegment(), and
 * deleteSegment() methods. After calling some number of these methods, calling
 * refreshDisplay() will make the changes visible.
 * 
 * @author Justin Johnson
 *
 */
public abstract class ConvexHullAlgorithm {

  private AnimationFrame currentFrame;

  private ConvexHullPanel panel;

  public ConvexHullAlgorithm() {
    currentFrame = new AnimationFrame();
  }

  /**
   * Computes the convex hull of a set of points. Subclasses must implement
   * this method.
   * 
   * @param points The points for which the convex hull is to be computed.
   * @return A List<Point> containing the convex hull of the input set, where
   * the points are ordered either clockwise or counterclockwise around the
   * bounding polygon.
   */
  public abstract List<Point> convexHull(Set<Point> points);

  /**
   * Draws the specified point.
   */
  protected final void drawPoint(Point p) {
    currentFrame.addPoint(p);
  }

  /**
   * Deletes the specified point.
   */
  protected final void deletePoint(Point p) {
    currentFrame.removePoint(p);
  }

  /**
   * Draws the specified line segment.
   */
  protected final void drawSegment(LineSegment segment) {
    currentFrame.addLineSegment(segment);
  }

  /**
   * Draws a line segment between the two specified points.
   */
  protected final void drawSegment(Point start, Point end) {
    drawSegment(new LineSegment(start, end));
  }

  /**
   * Deletes the specified line segment.
   */
  protected final void deleteSegment(LineSegment segment) {
    currentFrame.removeLineSegment(segment);
  }

  /**
   * Deletes the line segment between the two specified points.
   */
  protected final void deleteSegment(Point start, Point end) {
    deleteSegment(new LineSegment(start, end));
  }

  /**
   * Updates the display to draw and delete the points and line segments that
   * have been drawn or deleted since the last call to refreshDisplay().
   */
  protected final void refreshDisplay() {
    currentFrame.finalize();
    panel.scheduleFrame(currentFrame);
    currentFrame = new AnimationFrame();
  }

  public final void setPanel(ConvexHullPanel panel) {
    this.panel = panel;
  }

}
