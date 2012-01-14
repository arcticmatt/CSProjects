/*
 * GameLogic.java
 * Implements all the logic for Missile Command.
 *
 * Authors:       Joseph Gonzalez, Robert Morell, Brian Emre Aydemir
 */
package missilecommand;

import java.util.*;

/**
 * Implements all the logic for Missile Command.
 **/
public class GameLogic {

  /** The space to put between buildings, in pixels. **/
  public final static int BUILDING_SPACING = 30;

  /** The width of each building, in pixels. **/
  public final static int BUILDING_WIDTH = 60;

  /** The maximum height to make each building, in pixels. **/
  public final static int MAX_HEIGHT = 50;

  /** Used to determine the velocity of newly created missiles. **/
  public final static int MAX_VEL = 10;

  /** Power regeneration rate. **/
  public final static float POWER_REGEN = .005F;

  /** Power loss rate (per click). **/
  public final static float POWER_HIT = .01F;

  /**
   * Random number generator.
   * Defaults to a generator seeded with the current time.
   **/
  private Random gen = new Random();

  /**
   * Creates a new instance of GameLogic with all fields set to their
   * default values.
   **/
  public GameLogic() { }

  /** Runs the game for one time step. **/
  public void updateCycle(GameState state) {
    createMissiles(state);
    updateMissiles(state);
    updatePlayerInput(state);
    updateExplosions(state);

    if(state.power < 1.0) {
      state.power += POWER_REGEN;
    }
  }

  /**
   * Updates the state of the missiles by one time step by moving them
   * appropriately and checking for collisions with buildings.
   **/
  private void updateMissiles(GameState state) {
    Iterator missiles = state.missiles.iterator();
    boolean buildingHit;

    // Iterate over each currently active missile.
    while(missiles.hasNext()) {
      Missile m = (Missile)missiles.next();
      // Move the missile and get its new location.
      m.move();
      Vector2D location = m.getLocation();

      if (location.getIComp() < 0 ||
          location.getIComp() > state.worldWidth)
      {
        // Missile went off the left or right edge of the screen.
        missiles.remove();
      }
      else if (location.getJComp() < 0)
      {
        // The missile hit the ground.
        location.setJComp(0);
        missiles.remove();
        buildingHit = true;
        state.explosions.add(m.explode());
      }
      else
      {
        // Loop over all the buildings to see if we hit any of them.
        buildingHit = false;
        for(Building b : state.buildings) {
          if(b.isInterior(m.getLocation())) {
            missiles.remove();
            buildingHit = true;
            state.explosions.add(m.explode());
          }
        }		
      }
    }
  }

  /** Adds missiles to the game (as defined by the state object). **/
  private void createMissiles(GameState state) {
    // TODO: add code here to create missiles.


  }

  /**
   * Processes all the clicks by the user since this method
   * was last called.
   **/
  private void updatePlayerInput(GameState state){
    Iterator clicks = state.playerClicks.iterator();
    boolean isExplosionClick = true;

    // Loop over all the clicks.
    while (clicks.hasNext()) {
      Vector2D c = (Vector2D)clicks.next();
      clicks.remove();

      // Used to indicate if c should create an explosion or not.
      isExplosionClick = true;

      // Loop over all the buildings to see if the player clicked on one.
      for(Building b : state.buildings) {
        if (b.isInterior(c)) {
          // Clicked inside a building.
          // TODO: Possibly make this a bit more interesting.

          // Don't create an explosion by clicking on a building.
          isExplosionClick = false;
        }
      }

      // Check to see if we need to create an explosion.
      if (isExplosionClick && state.power > 0.0F) {
        // TODO: modify what happens when an explosion is created.


        // Decrease the power so that future explosions are smaller.
        state.power -= POWER_HIT;

        // Create an explosion.  The maximum size depends on the power.
        state.explosions.add(new Explosion(c, (int)(50*state.power), 5));
      }
    }
  }

  /**
   * Check to see if explosions have destroyed missiles or buildings.
   **/
  private void updateExplosions(GameState state) {
    Iterator explosions = state.explosions.iterator();

    // Used to keep track of any new explosions, such as ones
    // created when an existing explosion destroys a missile.
    LinkedList<Explosion> newExplosions = new LinkedList<Explosion>();

    // Loop over the current set of explosions.
    while (explosions.hasNext()) {
      Explosion e = (Explosion)explosions.next();

      if (e.explode()) {
        // The explosion has reached its maximum size.
        explosions.remove();
      } else {
        // Check if the explosion has destroyed a missile.
        Iterator missiles = state.missiles.iterator();
        while (missiles.hasNext()) {
          Missile m = (Missile)missiles.next();
          if (e.intersects(m)) {
            // Explosion intersects a missile.
            // TODO: make something interesting happen here.

          }
        }

        // Check if the explosion hit a building.
        Iterator buildings = state.buildings.iterator();
        while (buildings.hasNext()) {
          Building b = (Building)buildings.next();
          if (e.intersects(b)) {
            // Explosion intersects a building.
            // TODO: make something interesting happen here.


          }
        }
      }
    }

    // Add all the new explosions now.
    state.explosions.addAll(newExplosions);
  }

  /**
   * Initializes the game state by setting the power and adding
   * buildings.
   **/
  public void initializeGameState(GameState state) {
    initializeSkyLine(state);
    state.power = 1.0F;
  }

  /** Adds buildings to the game (as defined by the state object). **/
  private void initializeSkyLine(GameState state) {
    // TODO: add code to create a "skyline" here.

  }

  /** Returns true if and only if the game is over. **/
  public boolean isGameOver(GameState state) {
    // TODO: Change this to end the game at an opportune time.

    return false;
  }

}
