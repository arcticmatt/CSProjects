/**
 * A PendulumIntegrator can update a Pendulum given a timestep.
 * 
 * @author Justin Johnson
 */
public interface PendulumIntegrator {

  /**
   * Updates the angle and velocity of a Pendulum based on its current angle
   * and velocity.
   * @param pendulum The Pendulum to update.
   * @param h The timestep.
   */
  public void takeStep(Pendulum pendulum, double h);
  
}
