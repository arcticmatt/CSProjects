package missilecommand;

/**
 * A class to represent two dimensional vectors with integer components.
 */
public class Vector2D {
	//i,j represent the i / j components of the vector.
	private int i, j;
	/**
	 * Creates a new Vector2D with zero magnitude.
	 */
	public Vector2D() {
		/* A zero magnitude vector means its components
		 * are all 0.
		 */
		i = 0;
		j = 0;
	}


  /**
   * Creates a new Vector2D with specified i and j components.
   * 
   * @param i The initial i component of the new Vector2D.
   * @param j The initial j component of the new Vector2D.
   */
  public Vector2D(int i, int j) {
    this.i = i;
    this.j = j;
  }

  /**
   * Adds another Vector2D this this Vector2D and returns the the resulting
   * vector.
   * 
   * @param v The Vector2D to be added to this Vector2D.
   * @return The resulting Vector2D (this).
   */
  public Vector2D add(Vector2D v) {
	  int newI = v.getIComp() + getIComp();
	  int newJ = v.getJComp() + getJComp();
	  //get the new component values & set them.
	  setIComp(newI);
	  setJComp(newJ);
	  return this;
  }

  /**
   * Subtracts another Vector2D from this Vector2D and returns the resulting
   * vector.
   * 
   * @param v The Vector2D to be subtracted from this Vector2D.
   * @return The resulting Vector2D (this).
   */
  public Vector2D subtract(Vector2D v) {
    int newI = getIComp() - v.getIComp();
    int newJ = getJComp() - v.getJComp();
    //get the new component values and set them.
    setIComp(newI);
    setJComp(newJ);
    return this;
  }

  /**
   * Computes the dot product between this Vector2D and another Vector2D.
   * 
   * @param v The other Vector2D.
   * @return The dot product betweeen this and v.
   */
  public int dot(Vector2D v) {
	  //dot product = sum of products of components of vectors
	  return (getIComp()*v.getIComp() + getJComp()*v.getJComp());
  }

  /**
   * Computes the Euclidean distance between the point represented by this
   * Vector2D and the point represented by another Vector2D.
   * 
   * @param v The other Vector2D.
   * @return The Euclidean distance between this and v.
   */
  public double distanceTo(Vector2D v) {
	  int deltaI = getIComp() - v.getIComp();
	  int deltaJ = getJComp() - v.getJComp();
	  /* By the pythagorean theorem, distance = 
	   * sqrt(|i0 - i1|^2 + |j0 - j1|^2)
	   */
	  double distanceSquared = (double)(deltaI*deltaI + deltaJ*deltaJ);
	  return Math.sqrt(distanceSquared);
  }

  /**
   * Computes the magnitude of this Vector2D.
   * 
   * @return The magnitude of this Vector2D.
   */
  public double magnitude() {
    /* The magnitude of a 2D vector = sqrt(i^2 + j^2)
     */
	double magnitudeSquared = (double)(getIComp()*getIComp() + 
			getJComp()*getJComp());
	return Math.sqrt(magnitudeSquared);
  }

  /**
   * Gets the i component of this Vector2D.
   * 
   * @return The i component.
   */
  public int getIComp() {
	  return this.i;
  }

  /**
   * Gets the j component of this Vector2D.
   * 
   * @return The j component.
   */
  public int getJComp() {
	  return this.j;
  }

  /**
   * Sets the i component of this Vector2D.
   * 
   * @param i The value to which the i component will be set.
   */
  public void setIComp(int i) {
    this.i = i;
  }

  /**
   * Sets the j component of this Vector2D.
   * 
   * @param j The value to which the j component will be set.
   */
  public void setJComp(int j) {
    this.j = j;
  }

  /**
   * Generates a String representation of this Vector2D.
   * 
   * @return A String of the form "<i, j>"
   */
  public String toString() {
    return String.format("<%d, %d>", i, j);
  }
}
