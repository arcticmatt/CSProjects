import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.Queue;

import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * Provides custom drawing for visualizing in-place comparison sorts.
 * 
 * @author Justin Johnson
 */
@SuppressWarnings("serial")
public class SortingPanel extends JPanel {

  // The delay (in milliseconds) between showing each operation.
  private static final int DELAY = 10;
  
  // The ratio of the width of the bars to the width of the gaps between bars.
  private static final double BAR_WIDTH = 3.0;

  // The color of most bars.
  private static final Color INACTIVE_COLOR = Color.BLACK;

  // The color of bars being swapped.
  private static final Color SWAP_COLOR = Color.RED;

  // The color of bars being compared.
  private static final Color COMPARE_COLOR = Color.BLUE;

  // The heights of the bars to draw.
  private double[] barHeights;

  // The largest and smallest values stored in barHeights
  private double minHeight;
  private double maxHeight;

  // These are used for animation.
  private Iterator<Operation> opIterator;
  private Timer timer;
  private Operation lastOp = null;

  /**
   * Specifies the initial size. The component can be freely resized.
   */
  public Dimension getPreferredSize() {
    return new Dimension(500, 500);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (!(g instanceof Graphics2D)) {
      System.err.println("Does not have Graphics2D");
      System.exit(1);
    }

    Graphics2D g2 = (Graphics2D) g;

    Rectangle bounds = g2.getClipBounds();
    g2.clearRect(0, 0, bounds.width, bounds.height);

    double minY = Math.min(0, minHeight);
    double maxY = Math.max(0, maxHeight);

    double widthUnit = bounds.getWidth() / ((1 + BAR_WIDTH) * barHeights.length + 1);
    double heightUnit = bounds.getHeight() / (maxY - minY);

    for (int i = 0; i < barHeights.length; i++) {
      double x = ((i + 1 + i * BAR_WIDTH) * widthUnit);
      double y = 0;
      if (barHeights[i] > 0) {
        y = bounds.getHeight() - (barHeights[i] - minY) * heightUnit;
      } else {
        y = bounds.getHeight() + minY * heightUnit;
      }
      double w = (BAR_WIDTH * widthUnit);
      double h = Math.abs(barHeights[i]) * heightUnit;

      if (lastOp != null && (i == lastOp.getIndex1() || i == lastOp.getIndex2())) {
        if (lastOp.getType() == Operation.Type.SWAP) { 
          g2.setColor(SWAP_COLOR);
        } else if (lastOp.getType() == Operation.Type.COMPARE) {
          g2.setColor(COMPARE_COLOR);
        }
      } else {
        g2.setColor(INACTIVE_COLOR);
      }

      Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
      g2.fill(rect);
    }
  }

  /**
   * Animate the specified series of array operations.
   */
  public void animate(Queue<Operation> ops) {
    if (timer != null) {
      timer.stop();
    }
    timer = new Timer(DELAY, new SortingActionListener());
    opIterator = ops.iterator();
    timer.start();
  }

  public void setArray(double[] array) {
    minHeight = Double.POSITIVE_INFINITY;
    maxHeight = Double.NEGATIVE_INFINITY;
    barHeights = new double[array.length];
    for (int i = 0; i < array.length; i++) {
      barHeights[i] = array[i];
      if (array[i] < minHeight) minHeight = array[i];
      if (array[i] > maxHeight) maxHeight = array[i];
    }

    lastOp = null;
    opIterator = null;

    // If we don't stop the old timer then it will keep running even though we don't keep a
    // reference to it.
    if (timer != null) {
      timer.stop();
      timer = null;
    }
    repaint();
  }

  private class SortingActionListener implements ActionListener {

    /**
     * This method is called whenever the timer fires.
     */
    @Override
    public void actionPerformed(ActionEvent e) {		
      if (opIterator != null && opIterator.hasNext()) {
        Operation op = opIterator.next();
        if (op.getType() == Operation.Type.SWAP) {
          int i1 = op.getIndex1();
          int i2 = op.getIndex2();
          double temp = barHeights[i1];
          barHeights[i1] = barHeights[i2];
          barHeights[i2] = temp;
        }
        lastOp = op;
      } else if (timer != null) {
        // If we don't stop the old timer then it will keep running even though we don't keep a
        // reference to it.
        lastOp = null;
        timer.stop();
      } else {
        lastOp = null;
      }
      repaint();
    }

  }

}
