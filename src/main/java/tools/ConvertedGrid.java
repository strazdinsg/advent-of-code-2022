package tools;

/**
 * Can convert a String grid into another grid.
 */
public abstract class ConvertedGrid {

  /**
   * Creates a specific grid from the string grid. Uses the transformCell function
   * implemented in child classes.
   *
   * @param stringGrid The source grid of strings
   * @throws IllegalArgumentException When some grid values can't be converted
   */
  public void initializeFrom(StringGrid stringGrid) throws IllegalArgumentException {
    if (stringGrid == null || stringGrid.getRowCount() == 0 || stringGrid.getColumnCount() == 0) {
      throw new IllegalArgumentException("Can't convert empty string grid");
    }

    createEmptyGrid(stringGrid.getRowCount(), stringGrid.getColumnCount());

    for (int i = 0; i < stringGrid.getRowCount(); ++i) {
      String row = stringGrid.getRow(i);
      for (int j = 0; j < row.length(); ++j) {
        setCellValueFromChar(i, j, row.charAt(j));
      }
    }
  }

  /**
   * Set value of a specific cell from the char value in the string grid.
   *
   * @param row    The row of the grid where the value must be placed
   * @param column The column of the grid where the value must be placed.
   * @param c      The char value to be converted
   * @throws IllegalArgumentException When the character c can't be interpreted properly
   */
  protected abstract void setCellValueFromChar(int row, int column, char c)
      throws IllegalArgumentException;

  /**
   * Create the necessary grid data structures, with empty values.
   *
   * @param rowCount    number of rows in the grid
   * @param columnCount number of columns in the grid
   */
  protected abstract void createEmptyGrid(int rowCount, int columnCount);
}
