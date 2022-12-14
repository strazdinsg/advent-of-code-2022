package tools;

import java.util.Objects;

/**
 * A two-dimensional position (vector).
 */
public class Vector {
  private int x;
  private int y;

  /**
   * Create a new position.
   *
   * @param x The x-dimension of the position
   * @param y The y-dimension of the position
   */
  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Checks if this is a zero vector.
   *
   * @return True if this is a zero-vector (0, 0), false otherwise.
   */
  public boolean isZero() {
    return x == 0 && y == 0;
  }

  /**
   * Create a new vector: this vector minus the provided vector v. This vector is unchanged!
   *
   * @param v The vector to subtract
   * @return New vector: this vector minus the provided vector v
   */
  public Vector minus(Vector v) {
    return new Vector(this.x - v.x, this.y - v.y);
  }

  /**
   * Create a new vector: this vector plus the provided vector v. This vector is unchanged!
   *
   * @param v The vector to add
   * @return New vector: this vector plus the provided vector v
   */
  public Vector plus(Vector v) {
    return new Vector(this.x + v.x, this.y + v.y);
  }

  /**
   * Create a new vector: this vector plus the provided vector (x, y). This vector is unchanged!
   *
   * @param x The distance to add on the x-axis
   * @param y The distance to add on the y-axis
   * @return New vector: this vector plus the provided vector v
   */
  public Vector plus(int x, int y) {
    return new Vector(this.x + x, this.y + y);
  }

  /**
   * Create a new vector where the direction is kept intact but the length of the new vector
   * is one unit.
   * Warning: the implementation works with integers, so there may be errors if the vector
   * is not perpendicular to x-axis or y-axis!
   * The original vector (this vector) is kept intact
   *
   * @return A scaled vector
   */
  public Vector scaleToOneUnit() {
    int scaledX = x != 0 ? x / Math.abs(x) : 0;
    int scaledY = y != 0 ? y / Math.abs(y) : 0;
    return new Vector(scaledX, scaledY);
  }

  /**
   * Get the x coordinate of the vector.
   *
   * @return The x coordinate.
   */
  public int getX() {
    return x;
  }

  /**
   * Get the y coordinate of the vector.
   *
   * @return The y coordinate.
   */
  public int getY() {
    return y;
  }

  /**
   * Get the absolute value of the x-axis.
   *
   * @return The absolute value of the x-axis
   */
  public int getAbsoluteX() {
    return Math.abs(x);
  }

  /**
   * Get the absolute value of the y-axis.
   *
   * @return The absolute value of the y-axis
   */
  public int getAbsoluteY() {
    return Math.abs(y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vector vector = (Vector) o;
    return x == vector.x && y == vector.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }
}
