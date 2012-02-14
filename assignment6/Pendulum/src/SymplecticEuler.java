/**
 * An implementation of PendulumIntegrator that uses the symplectic Euler
 * method to update Pendulums.
 * 
 * @author Justin Johnson
 */
public class SymplecticEuler implements PendulumIntegrator {

  /**
   * Updates a Pendulum object using the symplectic Euler method.
   * @param pendulum The Pendulum to update.
   * @param h The timestep.
   */
  @Override
  public void takeStep(Pendulum pendulum, double h) {
    double omega = pendulum.getVelocity();
    double g = pendulum.getGravity();
    double L = pendulum.getLength();
    double theta = pendulum.getAngle();
    /* Now use the definition of the sympletic euler method to generate
     * the new theta / omega.
     */
    double newOmega = omega - h*g/L * Math.sin(theta);
    double newTheta = theta + h*newOmega;
    pendulum.setAngle(newTheta);
    pendulum.setVelocity(newOmega);
  }
  
}
