/*
 * GameController.java
 * Runs the Missile Command game and handles user input.
 *
 * Author:        Joseph Gonzalez
 * Modified by:   Brian Emre Aydemir
 * Created on November 16, 2003, 2:08 AM
 */
package missilecommand;

import java.awt.event.*;
import javax.swing.*;

/**
 * Runs the Missile Command game and handles user input.
 **/
public class GameController extends MouseAdapter implements Runnable {

  /** The state of the current game. **/
  private GameState state;

  /** The display that shows the current game to the user. **/
  private GameDisplay display;

  /** The scoreboard that displays the current game's score. **/
  private Scoreboard scoreboard;

  /** The logic engine to use to run the game. **/
  private GameLogic logic;

  /** Delay between ticks, in miliseconds.  **/
  private int frameDelay;

  /** Creates a new instance using the specified components. **/
  public GameController
  (GameDisplay display,
      Scoreboard scoreboard,
      GameLogic logic,
      int frameDelay)
  {
    this.display = display;
    this.logic = logic;
    this.scoreboard = scoreboard;
    this.frameDelay = frameDelay;
    display.addMouseListener(this);
  }

  /** Starts a new game. **/
  public void startGame() {
    state = new GameState();
    state.worldWidth = display.getWidth();
    state.worldHeight = display.getHeight();
    logic.initializeGameState(state);

    Thread gameThread = new Thread(this);
    gameThread.start();
  }

  /** Runs the game. **/
  public void run() {

    while (logic.isGameOver(state) == false) {
      synchronized (state.playerClicks) {
        logic.updateCycle(state);
      }
      display.update(state);

      // Must modify the scoreboard from within the even handling thread.
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          scoreboard.updateScoreboard(state);
        }
      } );

      // Force a repaint and introduce a slight delay.
      display.repaint(1);
      try { Thread.sleep(frameDelay); } catch (Exception e) { /* Ignored. */ }
    }

    JOptionPane.showMessageDialog(display, "Your Score: " + state.score,
        "Game Over", JOptionPane.OK_OPTION);

    startGame();


  }

  /** Registers a click whenever a mouse button is pushed down. **/
  public void mousePressed(MouseEvent e) {
    if(state != null) {
      synchronized (state.playerClicks) {
        state.playerClicks.add
        (new Vector2D(e.getX(), display.getHeight() - e.getY()));
      }
    }
  }

}
