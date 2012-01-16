/*
 * MissileCommandApplet.java
 *
 * Created on January 24, 2004, 4:22 PM
 */

/**
 *
 * @author  Joseph E. Gonzalez
 */
package missilecommand;

import javax.swing.*;
import java.awt.*;

public class MissileCommandApplet extends JApplet {

  /** Initialization method that will be called after the applet is loaded
   *  into the browser.
   */
  public void init() {

    JPanel screenContents = new JPanel(new BorderLayout());
    Scoreboard scoreboard = new Scoreboard();
    GameDisplay display =
      new GameDisplay
      (getWidth(),
          getHeight()-scoreboard.getPreferredSize().height);

    screenContents.add(scoreboard, BorderLayout.NORTH);
    screenContents.add(display, BorderLayout.CENTER);
    getContentPane().add(screenContents);

    int frameDelay = 40;

    try {
      frameDelay = Integer.parseInt(getParameter("frameDelay"));
    } catch (NumberFormatException exn) {
      // Ignored.  Default to above value.
    }

    GameLogic logic = new GameLogic();
    GameController control =
      new GameController(display, scoreboard, logic, frameDelay);
    control.startGame();
  }

}
