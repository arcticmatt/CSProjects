/**
 * An implementation of the Fourier transform that uses naive iteration.
 * 
 * @author Justin Johnson
 *
 */
public class NaiveFourierTransform extends FourierTransform {

  /**
   * Computes the discrete Fourier transform of the input data using naive
   * iteration.
   * @param input The complex numbers to be transformed.
   * @return An array of complex numbers containing the discrete Fourier
   * transform of the input data.
   */
  public ComplexNumber[] transform(ComplexNumber[] input) {
    int n = input.length;
    ComplexNumber[] output = new ComplexNumber[n];
    for (int k = 0; k < n; k++) {
      output[k] = ComplexNumber.ZERO;
      for (int j = 0; j < n; j++) {
        ComplexNumber w = ComplexNumber.getRootOfUnity(n, k * j);
        ComplexNumber term = input[j].times(w);
        output[k] = output[k].plus(term);
      }
    }
    return output;
  }

}
