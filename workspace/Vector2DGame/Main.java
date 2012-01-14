/**
 *
 * @author josephg
 */
public class Main {

  /**
   * Creates a new instance of Main.
   **/
  public Main() {
  }

  /**
   * The main method.
   * 
   * @param args the command line arguments
   */
  
  public static void p(String output) {
	  //prints the output to stdout
	  System.out.println(output);
  }
  
  public static void main(String[] args) {
	  // Testing Addition:
	  Vector2D a = new Vector2D(1,2);
	  Vector2D b = new Vector2D(3,4);
	  System.out.println("The initial value of a: " + a);
	  System.out.println("The initial value of b: " + b);
	  a.add(b);
	  System.out.println("The final value of a: " + a);
	  System.out.println("The final value of b: " + b);
	  /* test the distance formula, magnitude formula, dot 
	  * product, subtraction, setIComp, setJComp, getIComp, getJComp,
	  * and initialization of a blank vector */
	  p("Distance = " + a.distanceTo(b));
	  p("a's magnitude = " + a.magnitude());
	  p("b's magnitude = " + b.magnitude());
	  p("dot product = " + a.dot(b));
	  p("b - a = " + b.subtract(a));
	  p("b = " + b);
	  p("Setting b = <5,42>");
	  b.setIComp(5);
	  b.setJComp(42);
	  p("Now, b = " + b);
	  p("Which equals " + b.getIComp() + "," + b.getJComp());
	  Vector2D x = new Vector2D();
	  p("blank vector = " + x);
  }
}
