import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * A PendulumApplication drives the pendulum simulation project.
 * 
 * @author Justin Johnson
 */
public class PendulumApplication {
  
  // The delay (in milliseconds) between frames of animation.
  private static final int DELAY = 20;
  
  // The size of the font for the energy display.
  private static final int FONT_SIZE = 14;
  
  // The pendulum to animate.
  private Pendulum pendulum;
  
  // The panel which draws the pendulum.
  private PendulumPanel panel;
  
  // The label used to display the energy.
  private JLabel label;
  
  // The numeric integrator.
  private PendulumIntegrator integrator;
  
  // The step size for the integrator.
  private double timeStep;
  
  // The timer to control simulation and animation.
  private Timer timer;
 
  /**
   * Creates and starts the execution of a new PendulumApplication.
   * @param pendulum The Pendulum to animate.
   * @param integrator The PendulumIntegrator that will be used to update the
   * pendulum.
   * @param timeStep The timestep to be used for integration.
   */
  public PendulumApplication(Pendulum pendulum, PendulumIntegrator integrator, 
      double timeStep) {
    
    this.pendulum = pendulum; 
    this.integrator = integrator;
    this.timeStep = timeStep;
    
    JFrame frame = new JFrame("Numeric Integration!");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    label = new JLabel("Energy  = ", SwingConstants.CENTER);
    //label.setFont(label.getFont().deriveFont(FONT_SIZE));
    label.setFont(new Font(Font.MONOSPACED, Font.PLAIN, FONT_SIZE));
    
    
    panel = new PendulumPanel(pendulum);
    frame.add(panel, BorderLayout.CENTER);
    frame.add(label, BorderLayout.SOUTH);
    frame.addKeyListener(new PendulumKeyListener());
    frame.pack();
    
    frame.setVisible(true);
    
    this.timer = new Timer(DELAY, new PendulumActionListener());
    timer.start();
  }
  
  /**
   * Private non-static inner class to to take time steps and update the panel.
   */
  private class PendulumActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      integrator.takeStep(pendulum, timeStep);
      // This is gross; replace with a better way to format a double
      int decimalPlaces = 5;
      double scale = Math.pow(10, decimalPlaces);
      String energy = "" + Math.round(scale * pendulum.getEnergy()) / scale;
      while (energy.length() < decimalPlaces + 2) {
        energy += "0";
      }
      label.setText("Energy = " + energy);
      panel.repaint();
    }
    
  }
  
  /**
   * Private non-static inner class to handle key events.
   */
  private class PendulumKeyListener extends KeyAdapter {
    
    @Override
    public void keyPressed(KeyEvent e) {
      switch (e.getKeyCode()) {
        case KeyEvent.VK_ESCAPE:
          System.exit(0);
          break;
      }
    }
  }
  
}
