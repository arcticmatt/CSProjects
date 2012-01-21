/**
 * An immutable object representing a line segment.
 * 
 * @author Justin Johnson
 * 
 */
public class LineSegment {

  private Point start, end;

  /**
   * Create a new LineSegment with specified starting and ending Points.
   */
  public LineSegment(Point start, Point end) {
    this.start = start;
    this.end = end;
  }

  /**
   * Get the starting Point of this segment.
   */
  public Point getStart() {
    return start;
  }

  /**
   * Get the ending Point of this segment.
   */
  public Point getEnd() {
    return end;
  }

  /**
   * Auto-generated hashcode method.
   */
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((start == null) ? 0 : start.hashCode());
    return result;
  }

  /**
   * Compare this LineSegment with another object. If the other object is a
   * LineSegment and if its endpoints are equal to this objects endpoints
   * then true is returned; otherwise false is returned.
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof LineSegment)) {
      return false;
    }

    LineSegment segment = (LineSegment) obj;
    return segment.getStart().equals(getStart()) 
        && segment.getEnd().equals(getEnd());
  }



}
