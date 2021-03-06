
/**
 * Contains the main entry point for the Fourier project. 
 * 
 * @author Justin
 *
 */
import java.util.*;
public class Main {
  
  /**
   * Entry point for the Fourier project. This method should ensure that 
   * NaiveFourierTransform and RecursiveFourierTransform give the same results
   * on the same input with a reasonable amount of floating point error.
   * 
   * @param args Command line arguments to the program. Not used.
   */
  public static void main(String[] args) {
	  /* Calculate the difference between the results of the two methods to ensure
	   * RecursiveFourierTransform works.
	   */
	  int arrayLength = 16;
	  ComplexNumber[] a1 = new ComplexNumber[arrayLength];
	  ComplexNumber[] a2 = new ComplexNumber[arrayLength];
	  for (int i = 0; i < arrayLength; i++) {
		  ComplexNumber c = rnd();
		  a1[i] = c;
		  a2[i] = c;
	  }
	  RecursiveFourierTransform recursive = new RecursiveFourierTransform();
	  NaiveFourierTransform naive = new NaiveFourierTransform();
	  /* Now run the different transforms, and get the difference between the results */
	  ComplexNumber[] recursiveResult = recursive.transform(a1);
	  ComplexNumber[] naiveResult = naive.transform(a2);
	  double difference = 0;
	  for (int i = 0; i < arrayLength; i++) {
		  ComplexNumber diff = naiveResult[i].minus(recursiveResult[i]);
		  //System.out.println(diff.norm());
		  difference += diff.norm();
	  }
	  System.out.println(difference);
  }
  
  private static ComplexNumber rnd() {
	  /* Generates a random complex number. */
	  Random gen = new Random();
	  double real = gen.nextDouble();
	  double imaginary = gen.nextDouble();
	  return new ComplexNumber(real, imaginary);
  }
  
}
