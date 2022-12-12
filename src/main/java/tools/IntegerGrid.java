package tools;

/**
 * Stores a grid of integers.
 */
public class IntegerGrid extends ConvertedGrid {
  private int[][] grid;

  /**
   * Get the number of rows stored in the grid.
   *
   * @return The number of rows. Zero when grid is empty.
   */
  public int getRowCount() {
    return grid.length;
  }

  /**
   * Get the number of columns in the grid.
   *
   * @return The number of columns, zero the grid is empty.
   */
  public int getColumnCount() {
    if (grid.length == 0) {
      return 0;
    }
    return grid[0].length;
  }

  /**
   * Get the value at specific row and column.
   *
   * @param row    The row index
   * @param column The column index
   * @return The cell value
   * @throws ArrayIndexOutOfBoundsException if the row or column index is out of bounds
   */
  public int getValueAt(int row, int column) throws ArrayIndexOutOfBoundsException {
    return grid[row][column];
  }

  @Override
  protected void setCellValueFromChar(int row, int column, char c) throws IllegalArgumentException {
    try {
      grid[row][column] = Integer.parseInt("" + c);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid integer value in the grid: " + c);
    }
  }

  @Override
  protected void createEmptyGrid(int rowCount, int columnCount) {
    grid = new int[rowCount][columnCount];
  }
}
