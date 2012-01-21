import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A class which extends JPanel to create custom graphics for drawing convex hulls. In particular,
 * a ConvexHullPanel stores a Set of Points and a List of Points. Each Point in the Set is drawn 
 * individually, and the List of Points is used to draw a closed polygon.
 * 
 * A ConvexHullPanel determines its own transformation from Point coordinates to window coordinates
 * so that all Points in both the Set and the List are visible.
 * 
 * @author Justin Johnson
 *
 */
@SuppressWarnings("serial")
public class ConvexHullPanel extends JPanel implements ActionListener {

  // The delay (in milliseconds) between each frame of the animation.
  private static final int DELAY = 40;

  // Specifies how much extra space there is around the points.
  private static final double PADDING = 0.1;

  // Radius (in pixels) of the non-hull Points.
  private static final double POINT_RADIUS = 3.5;

  // Color of the non-hull Points.
  private static final Color POINT_COLOR = Color.BLUE;

  // Radius (in pixels) of the hull Points.
  private static final double HULL_RADIUS = 5.5;

  // Color of the hull.
  private static final Color HULL_COLOR = new Color(63, 127, 63);

  // Width of the line (in pixels) for the hull.
  private static final float HULL_WIDTH = 4;

  // Radius (in pixels) of the emphasized points.
  private static final double EMPHASIZED_RADIUS = 4.5;

  // Color of the emphasized points.
  private static final Color EMPHASIZED_COLOR = Color.DARK_GRAY;

  // Width (in pixels) of the emphasized segments.
  private static final float EMPHASIZED_WIDTH = 2;

  // Color of the coordinate axis.
  private static final Color AXIS_COLOR = Color.BLACK;

  // Width (in pixels) of the coordinate axis.
  private static final float AXIS_WIDTH = 1;

  private Set<Point> points = null;
  private List<Point> hull = null;

  private double xMin, xMax;
  private double yMin, yMax;

  private Timer timer;

  // Points and lines that have been emphasized
  private Set<Point> emphasizedPoints = new HashSet<Point>();
  private Set<LineSegment> segments = new HashSet<LineSegment>();

  // The list of frames that have been scheduled.
  private Queue<AnimationFrame> frames = new LinkedList<AnimationFrame>();

  public ConvexHullPanel() {
    timer = new Timer(DELAY, this);
    timer.start();
  }

  /**
   * Specifies the initial size. The component can be resized freely.
   */
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }

  /**
   * Performs custom drawing for visualizing a convex hull.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (!(g instanceof Graphics2D)) {
      System.err.println("Does not have Graphics2D");
      System.exit(1);
    }

    Graphics2D g2 = (Graphics2D) g;

    if (points != null) {
      // Set up the affine transformation. 
      // We want xMin -> 0, xMax -> width, yMin -> height, yMax -> 0 
      Rectangle bounds = g2.getClipBounds();

      double xTrans = bounds.getWidth() * xMin / (xMin - xMax);
      double yTrans = bounds.getHeight() * yMax / (yMax - yMin);

      double xScale = bounds.getWidth() / (xMax - xMin);
      double yScale = bounds.getHeight() / (yMin - yMax);

      g2.setTransform(new AffineTransform(xScale, 0, 0, yScale, xTrans, yTrans));

      // Draw the coordinate axis
      g2.setColor(AXIS_COLOR);
      g2.setStroke(new BasicStroke(AXIS_WIDTH));
      drawLine(g2, new Point(xMin, 0), new Point(xMax, 0));
      drawLine(g2, new Point(0, yMin), new Point(0, yMax));

      // Draw the points
      if (points != null) {
        g2.setColor(POINT_COLOR);
        for (Point p : points) {
          drawPoint(g2, p, POINT_RADIUS, xScale, yScale);
        }
      }

      // Draw any emphasized points.
      if (emphasizedPoints != null) {
        g2.setColor(EMPHASIZED_COLOR);
        for (Point p : emphasizedPoints) {
          drawPoint(g2, p, EMPHASIZED_RADIUS, xScale, yScale);
        }                                 
      }

      // Draw any emphasized segments.
      if (segments != null) {
        g2.setColor(EMPHASIZED_COLOR);
        g2.setStroke(new BasicStroke(EMPHASIZED_WIDTH));

        for (LineSegment seg : segments) {
          drawLine(g2, seg);
        }
      }

      // Draw the hull only if there are no pending frames.
      if (hull != null && frames.isEmpty()) {
        g2.setColor(HULL_COLOR);
        g2.setStroke(new BasicStroke(HULL_WIDTH));

        Point last = null;
        Point first = null;
        for (Point cur : hull) {
          drawPoint(g2, cur, HULL_RADIUS, xScale, yScale);
          if (last == null) {
            first = cur;
          } else {
            drawLine(g2, last, cur);
          }
          last = cur;
        }
        drawLine(g2, last, first);
      }
    }
  }

  /**
   * Private helper method for drawing Points.
   */
  private void drawPoint(Graphics2D g2, Point p, double radius, double xScale, double yScale) {
    double x = p.getX() - radius / xScale;
    double y = p.getY() + radius / yScale;
    double w = 2 * radius / xScale;
    double h = 2 * radius / -yScale;
    g2.fill(new Ellipse2D.Double(x, y, w, h));
  }

  /**
   * Private helper method to draw a line segment.
   */
  private void drawLine(Graphics2D g2, LineSegment segment) {
    drawLine(g2, segment.getStart(), segment.getEnd());
  }

  /**
   * Private helper method for drawing a line between two points.
   */
  private void drawLine(Graphics2D g2, Point p1, Point p2) {
    /* If we were to simply draw a new Line2D with endpoints specified by the two points then
     * the entire line would be transformed; in particular the line width wouldn't remain
     * constant as the window was resized. Therefore we pull the transform out and manually
     * transform the endpoints.
     */
    AffineTransform transform = g2.getTransform();
    AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BICUBIC);

    // Set the transform to the identity.
    g2.setTransform(new AffineTransform());

    // Manually transform the endpoints.
    Point2D pp1 = op.getPoint2D(new Point2D.Double(p1.getX(), p1.getY()), null);
    Point2D pp2 = op.getPoint2D(new Point2D.Double(p2.getX(), p2.getY()), null);
    g2.draw(new Line2D.Double(pp1, pp2));

    // Set the transform back to what it was.
    g2.setTransform(transform);
  }

  /**
   * Sets the Set of Points for this ConvexHullPanel.
   */
  public void setPoints(Set<Point> points) {
    if (points == null || points.size() == 0) {
      this.points = null;
      return;
    }

    this.points = new HashSet<Point>();
    for (Point p : points) {
      this.points.add(p);
    }

    if (hull == null) {
      resetMinMax();
    }
    updateTransform(points);
  }

  /**
   * Sets the List of Points for this ConvexHullPanel.
   */
  public void setHull(List<Point> hull) {
    if (hull == null || hull.size() == 0) {
      this.hull = null;
      return;
    }

    this.hull = new LinkedList<Point>();
    for (Point p : hull) {
      this.hull.add(p);
    }

    if (points == null) {
      resetMinMax();
    }
    updateTransform(hull);
  }

  /**
   * Schedule a frame for animation.
   */
  public void scheduleFrame(AnimationFrame frame) {
    frames.add(frame);
  }

  /**
   * Resets the animation of this panel by deleting any pending frames and
   * removing any points or line segments that have been emphasized.
   */
  public void resetAnimation() {
    frames = new LinkedList<AnimationFrame>();
    emphasizedPoints = new HashSet<Point>();
    segments = new HashSet<LineSegment>();
  }

  /**
   * This method is called whenever the timer fires.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    applyFrame();
  }

  /**
   * Applies the next scheduled frame, if one exists.
   */
  private void applyFrame() {
    if (!frames.isEmpty()) {
      AnimationFrame frame = frames.poll();

      emphasizedPoints.addAll(frame.getPointsToAdd());
      emphasizedPoints.removeAll(frame.getPointsToRemove());

      segments.addAll(frame.getSegmentsToAdd());
      segments.removeAll(frame.getSegmentsToRemove());
    }
    repaint();
  }

  /**
   * Helper method to reset xMin, xMax, yMin, yMax to positive/negative infinity.
   */
  private void resetMinMax() {
    xMin = Double.POSITIVE_INFINITY;
    yMin = Double.POSITIVE_INFINITY;
    xMax = Double.NEGATIVE_INFINITY;
    yMax = Double.NEGATIVE_INFINITY;
  }

  /**
   * Private helper method to update the xMin, xMax, yMin, yMax so that all Points are visible.
   */
  private void updateTransform(Iterable<Point> points) {
    boolean changed = false;
    for (Point p : points) {
      double x = p.getX();
      double y = p.getY();
      if (x < xMin) {
        xMin = x;
        changed = true;
      }
      if (x > xMax) {
        xMax = x;
        changed = true;
      }
      if (y < yMin) {
        yMin = y;
        changed = true;
      }
      if (y > yMax) {
        yMax = y;
        changed = true;
      }
    }

    if (changed) {
      double xSpan = xMax - xMin;
      double ySpan = yMax - yMin;
      xMin -= PADDING * xSpan;
      xMax += PADDING * xSpan;
      yMin -= PADDING * ySpan;
      yMax += PADDING * ySpan;
    }

    repaint();		
  }

}