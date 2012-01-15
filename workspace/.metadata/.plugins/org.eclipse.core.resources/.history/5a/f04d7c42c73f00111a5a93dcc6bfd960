/*
 * Building.java
 * Implements a building in the Missile Command game world.
 *
 * Author:        Joseph Gonzalez
 * Modified by:   Brian Emre Aydemir
 */
package missilecommand;

/**
 * This class represents (implements) a "simple" two-dimensional building
 * in the missile command game world.
 **/
public class Building {

  /**
   * The top left coordinate of the building, from the user's perspective.
   * Missile command uses Quadrant 1 of the two-dimensional Cartesian
   * coordinate system.  Therefore the origin is at the bottom left corner
   * of the window.  The default value is null.
   **/
  private Vector2D topLeft;

  /**
   * The bottom right coordinate of the building, from the user's
   * perspective.  Missile command uses Quadrant 1 of the two-dimensional
   * Cartesian coordinate system.  Therefore the origin is at the bottom
   * left corner of the window.  The default values are null.
   **/
  private Vector2D bottomRight;

  /**
   * Describes the health of the building and ranges from 0 to 1 inclusive.
   * Default value is 1.0F.
   **/
  private float health = 1.0F;

  /**
   * Describes the repair rate of the building and ranges from 0 to 1
   * inclusive.  Default value is 0.2F.
   **/
  private float repairRate = 0.2F;

  /**
   * Describes the damage rate of the building and ranges from 0 to 1
   * inclusive.  Default value is 0.1F
   **/
  private float damageRate = 0.1F;

  /**
   * Creates a new Building instance with everything set to their default
   * values.
   */
  public Building() { }

  /**
   * Creates a new instance of the building with everything set to their
   * default value except the vectors describing the top left and bottom
   * right corners.  These vectors are given the values passed in as
   * arguments (the values should be non-null).
   **/
  public Building(Vector2D topLeft, Vector2D bottomRight) {
    this.topLeft = topLeft;
    this.bottomRight = bottomRight;
  }

  /** Get the object that defines the top left corner of the building. **/
  public Vector2D getTopLeft() { return topLeft; }

  /**
   * Set the the location of the top left corner of the building to the
   * argument without duplicating the argument.  The coordinate should be
   * specified from the user's perspective, i.e., under the assumption that
   * the game window is Quadrant 1 in the usual Cartesian plane with the
   * origin at the lower left corner.
   **/
  public void setTopLeft(Vector2D topLeft) { this.topLeft = topLeft; }

  /**
   * Returns the point object that defines the bottom right corner of the
   * buiding.
   **/
  public Vector2D getBottomRight() { return bottomRight; }

  /**
   * Set the location of the bottom right to the argument without
   * duplicating the argument.  The coordinate should be specified from the
   * user's perspective, i.e., under the assumption that the game window is
   * Quadrant 1 in the usual Cartesian plane with the origin at the lower
   * left corner.
   **/
  public void setBottomRight(Vector2D bottomRight) {
    this.bottomRight = bottomRight;
  }

  /**
   * Returns the current healt value of this buidling.  The value will be
   * between 0 and 1 inclusive.  A value of 0 indicates that the building
   * is dead, and 1 indicates that the building is completely healthy.
   **/
  public float getHealth() { return health; }

  /**
   * Sets the current health value of the building.  The health value must
   * be between 0 and 1.  If it is not then the health will automatically
   * be assigned the value 0.
   **/
  public void setHealth(float health) {
    if ((0.0F <= health) && (health <= 1.0F)) {
      this.health = health;
    } else {
      this.health = 0.0F;
    }
  }

  /**
   * Returns the repair rate of this building.  The repair rate will be
   * between 0 and 1 inclusive.
   **/
  public float getRepairRate() {
    return repairRate;
  }

  /**
   * Sets the repair rate of this building.  The repair rate must be
   * between 0 and 1 inclusive.  Everytime the building is repaired, the
   * health is increased by the repair rate.  If an invalid repair rate is
   * given then the repair rate is set to 0.
   **/
  public void setRepairRate(float repairRate) {
    if ((0.0F <= repairRate) && (repairRate <= 1.0F)) {
      this.repairRate = repairRate;
    } else {
      this.repairRate = 0.0F;
    }
  }

  /**
   * Returns the damage rate of this building.  The damage rate will be
   * between 0 and 1 inclusive.
   **/
  public float getDamageRate() {
    return damageRate;
  }

  /**
   * Sets the damage rate of this building.  The damage rate must be between
   * 0 and 1 inclusive.  Everytime the building is damaged, the health
   * is decreased by the damage rate.  If an invalid damage rate is given
   * then the damage rate is set to 0.
   **/
  public void setDamageRate(float damageRate) {
    if ((0.0F <= damageRate) && (damageRate <= 1.0F)) {
      this.damageRate = damageRate;
    } else {
      this.damageRate = 0.0F;
    }
  }

  /**
   * This method damages the bulding by reducing the health by the damage
   * rate.  If the building's health falls below 0, then it is set at 0.
   */
  public void damage() {
    health -= damageRate;
    if (health < 0.0F) {
      health = 0.0F;
    }
  }

  /**
   * If the health of the building is 0 then this method returns true.
   * otherwise this method returns false.
   **/
  public boolean isDestroyed() {
    // Just for safety using floating points we test that the health is
    // less than or equal to 0.0.
    return (health <= 0.0F);
  }

  /**
   * This method repairs the building by increasing the health of the
   * building by the repair rate. If the health exceeds 1 it is set at 1.
   **/
  public void repair() {
    health += repairRate;
    if (health > 1.0F) {
      health = 1.0F;
    }
  }

  /**
   * Determines if the argument vector is interior to the building.
   * The argument vector should not be null.
   **/
  public boolean isInterior(Vector2D v) {
    return (v.getJComp() <= topLeft.getJComp()) &&
        (topLeft.getIComp() <= v.getIComp()) &&
        (v.getIComp() <= bottomRight.getIComp());
  }

}
