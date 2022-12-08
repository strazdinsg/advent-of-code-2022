package problem.tools;

import tools.StringGrid;

/**
 * Stores a grid of integers.
 */
public class IntegerGrid {
  private int[][] grid;

  private IntegerGrid() {
  }

  /**
   * Create an integer grid from a string grid.
   *
   * @param stringGrid The string grid to interpret
   * @return Grid of integers
   * @throws IllegalArgumentException if the provided grid is either empty or any cell
   *                                  contains a non-integer value
   */
  public static IntegerGrid createFrom(StringGrid stringGrid) throws IllegalArgumentException {
    if (stringGrid == null || stringGrid.getRowCount() == 0 || stringGrid.getColumnCount() == 0) {
      throw new IllegalArgumentException("Can't convert empty string grid to integers");
    }

    IntegerGrid intGrid = new IntegerGrid();
    intGrid.grid = new int[stringGrid.getRowCount()][stringGrid.getColumnCount()];

    for (int i = 0; i < stringGrid.getRowCount(); ++i) {
      String row = stringGrid.getRow(i);
      for (int j = 0; j < row.length(); ++j) {
        try {
          intGrid.grid[i][j] = Integer.parseInt("" + row.charAt(j));
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Provided string grid contains non-integer values");
        }
      }
    }

    return intGrid;
  }


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
}
