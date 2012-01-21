import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Main class to drive the convex hull visualization application.
 * 
 * @author Justin Johnson
 *
 */
public class ConvexHullApplication implements KeyListener {

  private static final float FONT_SIZE = 14.0f;

  private static final String LABEL_TEXT = 
    "<html><center>" +
    "Press the spacebar to compute the convex hull. <br>" +
    "Press 'r' to generate a new set of points." +
    "</center></html>";

  JFrame frame;
  ConvexHullPanel hullPanel;
  
  private ConvexHullAlgorithm algorithm;
  
  private int numPoints;

  private Random rand = new Random();

  Set<Point> points;
  List<Point> hull;

  public ConvexHullApplication(ConvexHullAlgorithm algorithm, int numPoints) {
    this.algorithm = algorithm;
    this.numPoints = numPoints;
    
    JFrame frame = new JFrame("Convex Hull!");
    frame.setLayout(new BorderLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set up the label that shows the instructions.
    JLabel label = new JLabel(LABEL_TEXT, SwingConstants.CENTER);
    label.setFont(label.getFont().deriveFont(FONT_SIZE));

    hullPanel = new ConvexHullPanel();
    algorithm.setPanel(hullPanel);
    frame.add(hullPanel, BorderLayout.CENTER);
    frame.add(label, BorderLayout.SOUTH);
    frame.addKeyListener(this);
    frame.pack();
    refreshPoints();

    frame.setVisible(true);		
  }

  /**
   * Generates a new random set of points and resets the animation.
   */
  private void refreshPoints() {
    hull = null;
    points = new HashSet<Point>();
    for (int i = 0; i < numPoints; i++) {
      double x = 10 * (rand.nextDouble() - 0.5);
      double y = 10 * (rand.nextDouble() - 0.5);
      points.add(new Point(x, y));
    }
    hullPanel.resetAnimation();
    hullPanel.setHull(hull);
    hullPanel.setPoints(points);
  }

  /**
   * Computes and displays the convex hull of the current points.
   */
  private void computeHull() {
    hull = algorithm.convexHull(points);
    hullPanel.setHull(hull);
  }

  @Override
  public void keyPressed(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_SPACE :
        computeHull();
        break;
      case KeyEvent.VK_R :
        refreshPoints();
        break;
      case KeyEvent.VK_ESCAPE :
        System.exit(0);
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}

}
