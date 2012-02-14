/**
 * An implementation of PendulumIntegrator that uses the forward Euler method
 * to update Pendulums.
 * 
 * @author Justin Johnson
 */
public class ForwardEuler implements PendulumIntegrator {

  /**
   * Updates a Pendulum object using the forward Euler method.
   * @param pendulum The Pendulum to update.
   * @param h The timestep.
   */
  @Override
  public void takeStep(Pendulum pendulum, double h) {
	  double newAngle = pendulum.getAngle() + h*pendulum.getVelocity();
	  double dV = - h * pendulum.getGravity() / pendulum.getLength() * 
	  		Math.sin(pendulum.getAngle());
	  double newVelocity = pendulum.getVelocity() + dV; 
	  pendulum.setVelocity(newVelocity);
	  pendulum.setAngle(newAngle);
  }
  
}
