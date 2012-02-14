import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

/**
 * Provides custom drawing for the pendulum simluation application.
 * 
 * @author Justin Johnson
 */
@SuppressWarnings("serial")
public class PendulumPanel extends JPanel {
    
  // The length of the pendulum as a fraction of the screen size.
  private static final double PENDULUM_LENGTH = 0.45;
  
  // The default radius of the ball at the end of the pendulum as a fraction of
  // screen size.
  private static final double BALL_RADIUS = 0.025;
    
  // The Pendulum to draw.
  private Pendulum pendulum;
    
  /**
   * Creates a new PendulumPanel.
   * 
   * @param pendulum The pendulum to draw.
   */
  public PendulumPanel(Pendulum pendulum) {
    this.pendulum = pendulum;
  }
  
  /**
   * Gets the initial size (in pixels) of a PendulumPanel.
   */
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }
  
  /**
   * Draws the pendulum.
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    if (!(g instanceof Graphics2D)) {
      System.err.println("No Graphics2D; exiting");
      System.exit(1);
    }
    
    Graphics2D g2 = (Graphics2D)g;
    
    Rectangle bounds = g2.getClipBounds();
    double minDim = Math.min(bounds.getHeight(), bounds.getWidth());
    
    double cx = bounds.getCenterX();
    double cy = bounds.getCenterY();
    Point2D center = new Point2D.Double(cx, cy);
    
    double theta = pendulum.getAngle();
    
    double ex = cx + PENDULUM_LENGTH * minDim * Math.sin(theta);
    double ey = cy + PENDULUM_LENGTH * minDim * Math.cos(theta);
    Point2D end = new Point2D.Double(ex, ey);
    
    g2.draw(new Line2D.Double(center, end));
    
    double pr = BALL_RADIUS * minDim / pendulum.getLength();
    double px = ex - pr;
    double py = ey - pr;
    Ellipse2D ball = new Ellipse2D.Double(px, py, 2 * pr, 2 * pr);
    
    g2.fill(ball);
  }

}
