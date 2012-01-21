
/**
 * A class to represent a two-dimensional point in the plane. A Point has an x
 * coordinate and a y coordinate, and a Point may also store a data value.
 * Points can be compared by this data value. For example, to sort some set of
 * Points by polar angle, one could store this angle in the data field of each
 * Point.
 * 
 * @author Justin Johnson
 *
 */
public class Point implements Comparable<Point> {

  // The x coordinate of the Point.
  private double x;
  
  // The y coordinate of the Point.
  private double y;
  
  
  private double data;

  /**
   * Creates a new Point with specified coordinates.
   * @param x The initial x coordinate
   * @param y The initial y coordinate
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the x coordinate of this Point.
   * @return The x coordinate of this Point.
   */
  public double getX() {
    return x;
  }

  /**
   * Get the y coordinate of this Point.
   * @return The y coordinate of this Point.
   */
  public double getY() {
    return y;
  }
  
  /**
   * Get the data stored in this Point.
   */
  public double getData() {
    return data;
  }
  
  /**
   * Set the data stored in this Point.
   * @param data The new data value to store in this Point.
   */
  public void setData(double data) {
    this.data = data;
  }

  /**
   * Get a String representation of this Point.
   */
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  /**
   * Auto-generated hashcode method.
   */
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  /**
   * Compare this Point to another object. If the other object is a Point and
   * if its x and y coordinates are equal to the coordinates of this Point
   * then true is returned; otherwise false is returned.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Point)) {
      return false;
    }

    Point p = (Point) obj;
    return p.getX() == getX() && p.getY() == getY();
  }
  
  /**
   * Compare two Point objects by their data values.
   */
  @Override
  public int compareTo(Point other) {
    return new Double(data).compareTo(new Double(other.data));
  }

}
