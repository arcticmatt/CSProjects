import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A class for visualizing the progress of a sorting algorithm.
 * 
 * @author Justin Johnson
 */
public class GraphicalSortingApplication {
  
  private static final String LABEL_TEXT = 
    "<html><center>" +
    "Press the spacebar to sort the array. <br>" +
    "Press 'r' to generate a new array." +
    "</center></html>";
  
  private static final float FONT_SIZE = 14.0f;

  private BaseSorter sorter;
  private JFrame frame;
  private SortingPanel panel;
  
  private int arraySize;

  private double[] array;

  /**
   * Creates a new GraphicalSortingApplication.
   * 
   * @param sorter The sorting implementation to visualize.
   * @param arraySize The size of the array to sort.
   * @throws InterruptedException If something bad happens.
   */
  public GraphicalSortingApplication(BaseSorter sorter, int arraySize) throws InterruptedException {
    this.sorter = sorter;
    this.arraySize = arraySize;
    
    // Set up the label that shows the instructions.
    JLabel label = new JLabel(LABEL_TEXT, SwingConstants.CENTER);
    label.setFont(label.getFont().deriveFont(FONT_SIZE));
    
    frame = new JFrame("Sorting!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new SortingPanel();
    frame.add(panel, BorderLayout.CENTER);
    frame.add(label, BorderLayout.SOUTH);
    frame.addKeyListener(new SortingKeyListener());
    frame.pack();

    frame.setVisible(true);

    refreshArray();
  }
  
  /**
   * Create a new array of the current size and pass it to the SortingPanel.
   */
  private void refreshArray() {
    array = new double[arraySize];
    for (int i = 0; i < arraySize; i++) {
      array[i] = 10 * (Math.random() - 0.5);
    }
    panel.setArray(array);
  }

  /**
   * Run the sorter and instructs the panel to animate the sorting procedure.
   * 
   */
  private void runSort() throws InterruptedException {
    sorter.sort(array);
    panel.animate(sorter.getOperations());
  }

  /**
   * Private nonstatic inner class to provide key listener functionality.
   * 
   * @author Justin
   */
  private class SortingKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_R :
          refreshArray();
          break;
        case KeyEvent.VK_SPACE :
          try {
            runSort();
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
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

}
