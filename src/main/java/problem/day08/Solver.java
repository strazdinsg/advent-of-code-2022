package problem.day08;

import problem.tools.IntegerGrid;
import tools.InputFile;
import tools.Logger;
import tools.StringGrid;

/**
 * Solution for the problem of Day 08
 * See description here: https://adventofcode.com/2022/day/8
 * Count the number of trees visible from at least one side. A tree is visible iff all trees to the
 * right, left, up or down are shorter than it.
 * The main idea: go through each row from left to right. At each position remember the tallest tree
 * to the left.Mark the current tree as visible if it is tallest so far. Then repeat the same
 * procedure for each row from right to left. Then for each column top-down and bottom up. Then
 * count the number of trees marked as tallest.
 */
public class Solver {

  private IntegerGrid treeHeights;
  private int rowCount;
  private int columnCount;
  private boolean[][] isVisible;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 08...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem08.input");
    if (!inputFile.exists()) {
      return;
    }

    StringGrid treeHeightsAsStrings = inputFile.readAllIntoGridBuffer();
    treeHeights = IntegerGrid.createFrom(treeHeightsAsStrings);
    rowCount = treeHeights.getRowCount();
    columnCount = treeHeights.getColumnCount();
    initializeEmptyVisibleTreeGrid();

    markVisibleTrees(LookAt.ROWS, Direction.LEFT_TO_RIGHT);
    markVisibleTrees(LookAt.ROWS, Direction.RIGHT_TO_LEFT);
    markVisibleTrees(LookAt.COLUMNS, Direction.TOP_DOWN);
    markVisibleTrees(LookAt.COLUMNS, Direction.BOTTOM_UP);

    Logger.info("Visible tree count: " + getVisibleTreeCount());
  }

  private int getVisibleTreeCount() {
    int visibleTreeCount = 0;
    for (int row = 0; row < rowCount; ++row) {
      for (int column = 0; column < columnCount; ++column) {
        if (isVisible[row][column]) {
          visibleTreeCount++;
        }
      }
    }
    return visibleTreeCount;
  }

  private void initializeEmptyVisibleTreeGrid() {
    isVisible = new boolean[rowCount][columnCount];
  }

  private void markVisibleTrees(LookAt lookAt, Direction direction) {
    if (lookAt == LookAt.ROWS) {
      markVisibleTreesByRows(direction);
    } else {
      markVisibleTreesByColumns(direction);
    }
  }

  private void markVisibleTreesByRows(Direction direction) {
    int fromColumn;
    int toColumn;
    int columnStep;
    if (direction == Direction.LEFT_TO_RIGHT) {
      fromColumn = 0;
      toColumn = columnCount;
      columnStep = 1;
    } else {
      fromColumn = columnCount - 1;
      toColumn = -1;
      columnStep = -1;
    }

    for (int row = 0; row < rowCount; ++row) {
      int maxHeightInRow = -1;
      for (int column = fromColumn; column != toColumn; column += columnStep) {
        int height = getTreeHeight(row, column);
        if (height > maxHeightInRow) {
          markAsVisible(row, column);
          maxHeightInRow = height;
        }
      }
    }
  }

  private void markVisibleTreesByColumns(Direction direction) {
    int fromRow;
    int toRow;
    int rowStep;
    if (direction == Direction.TOP_DOWN) {
      fromRow = 0;
      toRow = rowCount;
      rowStep = 1;
    } else {
      fromRow = rowCount - 1;
      toRow = -1;
      rowStep = -1;
    }

    for (int column = 0; column < columnCount; ++column) {
      int maxHeightInColumn = -1;
      for (int row = fromRow; row != toRow; row += rowStep) {
        int height = getTreeHeight(row, column);
        if (height > maxHeightInColumn) {
          markAsVisible(row, column);
          maxHeightInColumn = height;
        }
      }
    }
  }

  private void markAsVisible(int row, int column) {
    isVisible[row][column] = true;
  }

  private int getTreeHeight(int row, int column) {
    return treeHeights.getValueAt(row, column);
  }

}
