/**
 * A simple class to represent a swinging pendulum.
 * 
 * @author Justin Johnson
 *
 */
public class Pendulum {
  
  // The length of the pendulum arm.
  private double length;
  
  // The acceleration of gravity.
  private double gravity;
  
  // The current angle that the pendulum makes with the negative y-axis.
  private double angle;
  
  // The current angular velocity of the pendulum.
  private double velocity;
  
  /**
   * Creates a new Pendulum.
   * @param length The length of the pendulum arm.
   * @param gravity The force of gravity.
   * @param angle The initial angle.
   * @param velocity The initial velocity.
   */
  public Pendulum(double length, double gravity, double angle, double velocity) {
    this.length = length;
    this.gravity = gravity;
    this.angle = angle;
    this.velocity = velocity;
  }
  
  /**
   * Gets the length of the pendulum.
   */
  public double getLength() {
    return length;
  }
  
  /**
   * Gets the force of gravity.
   */
  public double getGravity() {
    return gravity;
  }
  
  /**
   * Gets the current angle of the pendulum.
   */
  public double getAngle() {
    return angle;
  }
  
  /**
   * Sets the current angle of the pendulum.
   * @param angle The new angle.
   */
  public void setAngle(double angle) {
    this.angle = angle;
  }
  
  /**
   * Gets the current angular velocity of the pendulum.
   */
  public double getVelocity() {
    return velocity;
  }
  
  /**
   * Sets the current angular velocity of the pendulum.
   * @param velocity The new angular velocity.
   */
  public void setVelocity(double velocity) {
    this.velocity = velocity;
  }
  
  /**
   * Gets the current energy of the pendulum.
   * @return The energy (potential + kinetic) of the pendulum.
   */
  public double getEnergy() {
    double height = length - length * Math.cos(angle);
    double v = length * velocity;
    double energy = gravity * height + 0.5 * v * v;
    return energy;
  }
                             

}
