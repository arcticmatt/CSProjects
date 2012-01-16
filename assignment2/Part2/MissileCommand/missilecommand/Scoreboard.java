/*
 * Scoreboard.java
 * Displays the current score and "power level" for Missile Command.
 *
 * Author:        Joseph Gonzalez
 * Modified by:   Brian Emre Aydemir
 */
package missilecommand;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

/**
 * Instances display the score and current power level of a Missile Command
 * game.  The current score and power level should be set through the {@link
 * #updateScoreboard(GameState)} method.
 **/
public class Scoreboard extends JPanel {

  /** The maximum power level. **/
  private final static int MAX_POWER = 1000;

  /** Displays the current score.  Should never be null. **/
  private JLabel score;

  /** Displays the current power level.  Should never be null. **/
  private JProgressBar power;

  /** Creates a panel that displays the current score and power level. **/
  public Scoreboard() {
    super(new FlowLayout(FlowLayout.CENTER));
    setBackground(Color.black);

    score = new JLabel("0");
    score.setForeground(Color.green);
    score.setBackground(Color.black);
    score.setFont(new Font("Arial", Font.BOLD, 24));
    add(score);

    power = new JProgressBar(JProgressBar.HORIZONTAL, 0, MAX_POWER);
    power.setForeground(Color.green);
    power.setBackground(Color.black);
    add(power);
  }

  /**
   * Updates the scoreboard's components (display) using the provided game
   * state data, which should not be null.  This method should be called
   * within the event handling thread.
   **/
  public void updateScoreboard(GameState state) {
    this.score.setText(new Integer(state.score).toString());
    this.power.setValue((int)(state.power * MAX_POWER));
  }

  /**
   * Shows a window with a scoreboard.  The JVM exits when the window is
   * closed.  This method provides a simple way to test this class.  The
   * arguments to this method are ignored.
   **/
  public static void main(String[] args) {
    JFrame testFrame = new JFrame("TestFrame");
    Scoreboard sb = new Scoreboard();

    testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    testFrame.getContentPane().add(sb);
    testFrame.pack();
    testFrame.setVisible(true);
  }

}
