/*
 * Explosion.java
 * Represents a simple two-dimensional explosion.
 *
 * Author:        Joseph Gonzalez
 * Modified by:   Brian Emre Aydemir
 */
package missilecommand;

import java.util.LinkedList;

/**
 * Instances of this class represent simple two-dimensional explosions.
 **/
public class Explosion {

  /** The location of the center of the explosion. **/
  private Vector2D location;

  /** The radius of the explosion measured in pixels.  Defaults to 0. **/
  private int currentRadius = 0;

  /** The messages to be displayed when the explosion dies **/
  private LinkedList<Message> messages = new LinkedList<Message>();
  
  /**
   * The maximum radius of the explosion measured in pixels.
   * The default value is 50 pixels.
   **/
  private int maxRadius = 40;

  /**
   * The growth speed measured in radius pixels per time step.
   * The default value is 5 radius pixels per time step.
   **/
  private int growthSpeed = 5;

  /**
   * Creates a new instance of Explosion, with everything set to its
   * default value.
   **/
  public Explosion() { }

  /**
   * Constructs an Explosion with a current radius of 0 centered at the
   * {@link Vector2D} location, with the specified maximum radius (in
   * pixels) and rate of growth (in pixels per time step).
   */
  public Explosion(Vector2D location, int maxRadius, int growthSpeed) {
    this.location = location;
    this.maxRadius = maxRadius;
    this.growthSpeed = growthSpeed;
  }

  /** Returns the location of the center of the explosion. **/
  public Vector2D getLocation() { return location; }

  /** Set the location of the center of the explosion. **/
  public void setLocation(Vector2D location) { this.location = location; }

  /** Returns the maximum radius of the explosion. **/
  public int getMaxRadius() { return maxRadius; }

  /** Set the maximum radius of the explosion. **/
  public void setMaxRadius(int maxRadius) { this.maxRadius = maxRadius; }

  /**
   * Returns the rate of growth (in pixels per time step) of the
   * explosion.
   **/
  public int getGrowthSpeed() { return growthSpeed; }

  /**
   * Sets how many pixels the radius of the explosion will grow every
   * time step.
   **/
  public void setGrowthSpeed(int growthSpeed) {
    this.growthSpeed = growthSpeed;
  }

  /** Returns the current radius of the explosion. */
  public int getCurrentRadius() { return currentRadius; }

  /**
   * Set the current radius of the explosion.  If this is greater than the
   * maximum radius then the maximum radius is used.
   */
  public void setCurrentRadius(int currentRadius ) {
    this.currentRadius =
      currentRadius > maxRadius ? maxRadius : currentRadius;
  }

  /**
   * Increase the explosion radius by the rate of growth for one time step.
   * Returns true if the explosion has reached its maximum radius otherwise
   * returns false.
   */
  public synchronized boolean explode() {
    return (currentRadius += growthSpeed) > maxRadius;
  }

  /**
   * Returns true if and only if this explosion intersects the missile
   * passed in as an argument.
   **/
  public boolean intersects(Missile missile) {
    return location.distanceTo(missile.getLocation()) <= currentRadius;
  }

  /** Adds a message to be displayed when the explosion finishes **/
  public void addMessage(Message m) {
	  messages.add(m);
  }
  
  public LinkedList<Message> getMessages() {
	  return messages;
  }
  
  /**
   * Returns true if and only if this explosion intersects the building
   * passed in as an argument.
   **/
  public boolean intersects(Building building) {
    // cache all variables:
    int left, right, top, myX, myY;
    left = building.getTopLeft().getIComp();
    right = building.getBottomRight().getIComp();
    top = building.getTopLeft().getJComp();
    myX = location.getIComp();
    myY = location.getJComp();

    boolean inX, inY;
    inX = ((myX < right) && (myX > left));
    inY = ((myY < top));

    //cases: (1) above top of building, to sides:
    if (!inX && !inY) { // only check 2 top corners
      return
      (location.distanceTo(building.getTopLeft()) < currentRadius) ||
      (location.distanceTo(new Vector2D(right, top)) < currentRadius);
    }

    // (2) directly on top of building.
    if (inX && !inY) {
      return ((myY - top) < currentRadius);
    }

    // (3) on either side of building.
    if (!inX && inY) {
      return
      ((myX > right) && ((myX - right) < currentRadius)) ||
      ((myX < left) && ((left - myX) < currentRadius));
    }

    // Must be inside.
    return true;
  }

}
