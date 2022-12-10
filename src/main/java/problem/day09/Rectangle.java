package problem.day09;

import tools.Vector;

/**
 * Represents rectangular boundaries.
 */
public class Rectangle {
  private final Vector topLeft;
  private final Vector bottomRight;

  /**
   * Create boundaries.
   *
   * @param minX x coordinate of top-left corner
   * @param minY y coordinate of top-left corner
   * @param maxX x coordinate of bottom-right corner
   * @param maxY y coordinate of bottom-right corner
   */
  public Rectangle(int minX, int minY, int maxX, int maxY) {
    topLeft = new Vector(minX, minY);
    bottomRight = new Vector(maxX, maxY);
  }

  /**
   * Get the width of the rectangle.
   *
   * @return The width
   */
  public int getWidth() {
    return bottomRight.getX() - topLeft.getX() + 1;
  }

  /**
   * Get the height of the rectangle.
   *
   * @return The height
   */
  public int getHeight() {
    return bottomRight.getY() - topLeft.getY() + 1;
  }

  /**
   * Get the top-left position.
   *
   * @return Position of the top-left corner
   */
  public Vector getTopLeft() {
    return topLeft;
  }

  /**
   * Get the bottom-right position.
   *
   * @return Position of the bottom-right corner
   */
  public Vector getBottomRight() {
    return bottomRight;
  }
}
