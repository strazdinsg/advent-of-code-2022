package problem.day12;

/**
 * A generic interface for a maze map.
 */
public interface MazeMap {
  /**
   * Get the height of the maze (number of rows).
   *
   * @return The height of the maze
   */
  int getHeight();

  /**
   * Get the width of the maze (number of columns).
   *
   * @return The width of the maze
   */
  int getWidth();

  /**
   * Check whether move from location (sourceRow, sourceColumn) to (destRow, destColumn) is allowed.
   *
   * @param sourceRow    The source row
   * @param sourceColumn The source column
   * @param destRow      The destination row
   * @param destColumn   The destination column
   * @return True if the move is allowed, false if it is not
   */
  boolean canMove(int sourceRow, int sourceColumn, int destRow, int destColumn);
}
