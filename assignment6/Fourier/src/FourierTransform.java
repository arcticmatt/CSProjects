/**
 * A FourierTransform object can perform various types of Fourier transform
 * operations. All operations are implemented in terms of the standard
 * (forward) Fourier transform. To use this class, subclasses should implement
 * the abstract transform() method. 
 * 
 * @author Justin Johnson
 *
 */
public abstract class FourierTransform {
  
  /**
   * The standard one-dimensional discrete (forward) Fourier transform.
   * Subclasses should provide their own implementation of this method.
   * @param input An array of complex numbers to transform.
   * @return The Fourier transform of input.
   */
  public abstract ComplexNumber[] transform(ComplexNumber[] input);
  
  /**
   * The standard one-dimensional forward Fourier transform with real input.
   * This utility method is implemented in terms of the abstract transform()
   * method.
   * @param input An array of real numbers to transform.
   * @return The Fourier transform of input.
   */
  public ComplexNumber[] transform(double[] input) {
    ComplexNumber[] complex = new ComplexNumber[input.length];
    for (int i = 0; i < input.length; i++) {
      complex[i] = ComplexNumber.fromReal(input[i]);
    }
    return transform(complex);
  }
  
  /**
   * Performs the one-dimensional inverse Fourier transform. This metho is
   * implemented in terms of the abstract transform() method.
   * @param input An array of complex numbers to transform.
   * @return The inverse Fourier transform of input.
   */
  public ComplexNumber[] inverseTransform(ComplexNumber[] input) {
    int n = input.length;
    ComplexNumber[] output = new ComplexNumber[n];
    for (int i = 0; i < n; i++) {
      output[i] = input[i].star();
    }
    output = transform(output);
    for (int i = 0; i < n; i++) {
      output[i] = output[i].star().times(1.0 / n);
    }
    return output;
  }

  /**
   * Performs a two-dimensional (forward) Fourier transform. This method is
   * implemented in terms of the abstract transform() method.
   * @param input A rectangular array of complex numbers to tranform.
   * @return The Fourier transform of input.
   */
  public ComplexNumber[][] transform(ComplexNumber[][] input) {
    int height = input.length;
    int width = input[0].length;
    ComplexNumber[][] step1 = new ComplexNumber[height][width];
    for (int i = 0; i < height; i++) {
      step1[i] = transform(input[i]);
    }
    ComplexNumber[][] step2 = new ComplexNumber[height][width];
    for (int i = 0; i < width; i++) {
      ComplexNumber[] column = new ComplexNumber[height];
      for (int j = 0; j < height; j++) {
        column[j] = step1[j][i];
      }
      column = transform(column);
      for (int j = 0; j < height; j++) {
        step2[j][i] = column[j];
      }
    }
    return step2;
  }
  
  /**
   * Performs a two-dimensional inverse Fourier transform. This method is
   * implemented in terms of the abstract transform() method.
   * @param input A rectangular array of complex numbers to transform.
   * @return The Fourier transform of input.
   */
  public ComplexNumber[][] inverseTransform(ComplexNumber[][] input) {
    int height = input.length;
    int width = input[0].length;
    ComplexNumber[][] output = new ComplexNumber[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j ++) {
        output[i][j] = input[i][j].star();
      }
    }
    output = transform(output);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        output[i][j] = output[i][j].star().times(1.0 / (height * width));
      }
    }
    return output;
  }
  
  /**
   * Helper method that subclasses can use to check if an integer is a power
   * of two. 
   */
  protected boolean powerOfTwo(int n) {
    return (n & (n - 1)) == 0;
  }
  
}
