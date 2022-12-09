package problem.day08;

/**
 * Data for looking in a specific direction.
 */
public class Sight {
  private int currentRow;

  private int currentColumn;
  private final int rowStep;
  private final int columnStep;
  private final int rowBoundary;
  private final int columnBoundary;


  private Sight(int currentRow, int currentColumn, int rowStep, int columnStep,
                int rowBoundary, int columnBoundary) {
    this.currentRow = currentRow;
    this.currentColumn = currentColumn;
    this.rowStep = rowStep;
    this.columnStep = columnStep;
    this.rowBoundary = rowBoundary;
    this.columnBoundary = columnBoundary;
  }

  /**
   * Create a sight from a specific location, in a specific direction.
   *
   * @param direction   The direction to look in
   * @param row         Look from the perspective of the tree at this row
   * @param column      Look from the perspective of the tree at this row
   * @param rowCount    The rowCount of the forest
   * @param columnCount The columnCount of the forest
   * @return A sight object with given configuration.
   * @throws IllegalStateException When illegal direction is provided
   */
  public static Sight fromDirection(Direction direction, int row, int column, int rowCount, int columnCount) {
    switch (direction) {
      case UP:
        return new Sight(row, column, -1, 0, -1, -1);
      case DOWN:
        return new Sight(row, column, 1, 0, rowCount, -1);
      case LEFT:
        return new Sight(row, column, 0, -1, -1, -1);
      case RIGHT:
        return new Sight(row, column, 0, 1, -1, columnCount);
      default:
        throw new IllegalArgumentException("Wrong direction for looking");
    }
  }

  /**
   * Check if we have reached the boundary of the forest (while advancing the sight).
   *
   * @return True when boundary is reached, false if not yet.
   */
  public boolean isBoundaryReached() {
    return currentRow == rowBoundary || currentColumn == columnBoundary;
  }

  /**
   * Advance the sight one step.
   */
  public void advanceOneStep() {
    currentRow += rowStep;
    currentColumn += columnStep;
  }

  /**
   * Get the current row where the "sight is focused"
   *
   * @return The current row of focus
   */
  public int getCurrentRow() {
    return currentRow;
  }

  /**
   * Get the current column where the "sight is focused"
   *
   * @return The current column of focus
   */
  public int getCurrentColumn() {
    return currentColumn;
  }
}
