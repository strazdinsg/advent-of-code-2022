package problem.day14;

import java.util.LinkedList;
import java.util.List;
import tools.Rectangle;
import tools.Vector;

/**
 * Represents a path of straight lines (horizontal or vertical).
 */
public class Path {
  private final List<Vector> corners = new LinkedList<>();
  private Rectangle boundaries;

  /**
   * Add one more corner to the path. The path will continue from the last-known corner to
   * this corner.
   *
   * @param corner The corner to add.
   */
  public void addCorner(Vector corner) {
    corners.add(corner);
    if (boundaries == null) {
      boundaries = new Rectangle(corner.getX(), corner.getY(), corner.getX(), corner.getY());
    } else {
      boundaries = boundaries.extend(corner);
    }
  }

  public Rectangle getBoundaries() {
    return boundaries;
  }

  /**
   * Get a list of corner coordinates, in the consecutive order.
   *
   * @return The corner coordinates of the path.
   */
  public List<Vector> getCorners() {
    return corners;
  }
}
