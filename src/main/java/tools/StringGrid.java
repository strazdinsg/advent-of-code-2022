package tools;

import java.util.ArrayList;
import java.util.List;

/**
 * A grid holding each row as a string of the sam length.
 */
public class StringGrid {
  private final List<String> rows = new ArrayList<>();
  private int columnCount = 0;

  /**
   * Append a row to the grid.
   *
   * @param row The row as a string
   * @throws IllegalArgumentException If the number of characters in this row differs from
   *                                  column count of the previously added rows
   */
  public void appendRow(String row) throws IllegalArgumentException {
    if (row == null || row.length() == 0) {
      throw new IllegalArgumentException("Can't add empty rows to grid");
    } else if (!rows.isEmpty() && row.length() != columnCount) {
      throw new IllegalArgumentException("Column count must be the same for all rows in the grid!");
    }

    columnCount = row.length();
    rows.add(row);
  }

  /**
   * Get the number of rows stored in the grid.
   *
   * @return The number of rows. Zero when grid is empty.
   */
  public int getRowCount() {
    return rows.size();
  }

  /**
   * Get the number of columns in the grid.
   *
   * @return The number of columns, zero the grid is empty.
   */
  public int getColumnCount() {
    return columnCount;
  }

  /**
   * Get the row at a specific index.
   *
   * @param rowIndex Index of the row to return. Indexing starts at zero.
   * @return The row
   * @throws IndexOutOfBoundsException if hte provided rowIndex is invalid
   */
  public String getRow(int rowIndex) throws IndexOutOfBoundsException {
    return rows.get(rowIndex);
  }
}
