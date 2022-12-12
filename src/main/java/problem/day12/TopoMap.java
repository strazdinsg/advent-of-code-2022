package problem.day12;

import tools.ConvertedGrid;
import tools.Vector;

/**
 * Topological map containing height values. The character heights get translated into
 * integers, where a corresponds to 0, b corresponds to 1, ..., z corresponds to 25.
 */
public class TopoMap extends ConvertedGrid implements MazeMap {
  private static final char START_POSITION_CHAR = 'S';
  private static final char END_POSITION_CHAR = 'E';

  private int[][] heights;
  private Vector startPosition;
  private Vector endPosition;

  @Override
  protected void createEmptyGrid(int rowCount, int columnCount) {
    heights = new int[rowCount][columnCount];
  }

  @Override
  protected void setCellValueFromChar(int row, int column, char c) {
    if (c == START_POSITION_CHAR) {
      startPosition = new Vector(column, row);
    } else if (c == END_POSITION_CHAR) {
      setHeightValue(row, column, 'z');
      endPosition = new Vector(column, row);
    } else {
      setHeightValue(row, column, c);
    }
  }

  private void setHeightValue(int row, int column, char c) throws IllegalArgumentException {
    int height = c - 'a';
    if (height < 0 || height > 25) {
      throw new IllegalArgumentException("Invalid height value: " + c);
    }
    heights[row][column] = height;
  }

  /**
   * Get the start position in the map.
   *
   * @return The start position
   */
  public Vector getStartPosition() {
    return startPosition;
  }

  /**
   * Get the end position in the map.
   *
   * @return The end position (destination)
   */
  public Vector getEndPosition() {
    return endPosition;
  }

  @Override
  public int getHeight() {
    return heights.length;
  }

  @Override
  public int getWidth() {
    return heights.length > 0 ? heights[0].length : 0;
  }

  @Override
  public boolean canMove(int sourceRow, int sourceColumn, int destRow, int destColumn) {
    int sourceHeight = heights[sourceRow][sourceColumn];
    int destinationHeight = heights[destRow][destColumn];
    return destinationHeight <= sourceHeight + 1;
  }
}
