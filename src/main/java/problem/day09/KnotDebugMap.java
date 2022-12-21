package problem.day09;

import tools.Rectangle;
import tools.Vector;

/**
 * A class for debugging the knot positions. Can print knots visually.
 */
public class KnotDebugMap {
  private final char[][] map;
  private final Rectangle boundaries;

  /**
   * Create knot-map for debugging.
   *
   * @param boundaries The boundaries (size) of the map
   */
  public KnotDebugMap(Rectangle boundaries) {
    map = new char[boundaries.getHeight()][boundaries.getWidth()];
    this.boundaries = boundaries;
    for (int row = 0; row < map.length; ++row) {
      for (int column = 0; column < map[0].length; ++column) {
        map[row][column] = '.';
      }
    }
  }

  /**
   * Plot a knot on the map.
   *
   * @param knotNumber   The number of the knot
   * @param knotPosition The position of the knot
   */
  public void addKnot(int knotNumber, Vector knotPosition) {
    Vector shiftedKnotPosition = knotPosition.minus(boundaries.getTopLeft());
    map[shiftedKnotPosition.getY()][shiftedKnotPosition.getX()] = ("" + knotNumber).charAt(0);
  }

  /**
   * Print the debug-map to the console.
   */
  public void print() {
    System.out.println();
    for (int row = 0; row < map.length; ++row) {
      for (int column = 0; column < map[0].length; ++column) {
        System.out.print(map[row][column]);
      }
      System.out.println();
    }
    System.out.println();
  }
}
