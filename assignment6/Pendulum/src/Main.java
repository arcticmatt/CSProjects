
/**
 * A class containing the main entry point for the Pendulum simulation project.
 * 
 * @author Justin Johnson
 *
 */
public class Main {
  
  // The length of the pendulum arm.
  private static final double LENGTH = 1.0;
  
  // The force of gravity.
  private static final double GRAVITY = 1.0;
  
  // The initial angular displacement (in radians) of the pendulum, measured
  // from the negative y axis.
  private static final double INITIAL_ANGLE = Math.PI / 2;
  
  // The initial angular velocity of the pendulum. Postive velocity corresponds
  // to counterclockwise rotation.
  private static final double INITIAL_VELOCITY = 0.0;
  
  // The timestep for numerical integration.
  private static final double TIMESTEP = 0.05;

  /**
   * This is the main entry point for the Pendulum simulation project.
   * 
   * @param args The command line arguments. Not used.
   */
  public static void main(String[] args) {
    Pendulum pendulum = new Pendulum(LENGTH, GRAVITY, INITIAL_ANGLE,
        INITIAL_VELOCITY);
    // Change this line to change the integration method!
    //PendulumIntegrator integrator = new ForwardEuler();
     PendulumIntegrator integrator = new BackwardEuler();
    //PendulumIntegrator integrator = new SymplecticEuler();

    new PendulumApplication(pendulum, integrator, TIMESTEP);
  }
  
}
