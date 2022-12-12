package problem.day08;

import tools.Direction;
import tools.InputFile;
import tools.IntegerGrid;
import tools.Logger;

/**
 * Solution for the problem of Day 08
 * See description here: https://adventofcode.com/2022/day/8
 * Count the number of trees visible from at least one side. A tree is visible iff all trees to the
 * right, left, up or down are shorter than it.
 * The main idea: iterate over all trees, look in all four directions. See how far the sight goes
 * in each direction.
 * Mark the tree as visible if the sight reaches the boundary in at least one direction
 */
public class Solver {

  private final IntegerGrid treeHeights = new IntegerGrid();
  private int rowCount;
  private int columnCount;
  private int visibleTreeCount;

  private boolean[][] isVisibleFromOutside;

  private int bestVisibility;

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
      Logger.info("Input file not found!");
      return;
    }

    treeHeights.initializeFrom(inputFile.readAllIntoGridBuffer());
    rowCount = treeHeights.getRowCount();
    columnCount = treeHeights.getColumnCount();
    initializeEmptyVisibilityData();

    // A brute-force search. Not an efficient algorithm, but should work just fine
    // for the small data set
    for (int row = 0; row < rowCount; ++row) {
      for (int column = 0; column < columnCount; ++column) {
        calculateVisibility(row, column);
      }
    }

    Logger.info("Visible tree count: " + getVisibleTreeCount());
    Logger.info("Highest scenic score: " + bestVisibility);
  }

  private int getVisibleTreeCount() {
    return visibleTreeCount;
  }

  private void initializeEmptyVisibilityData() {
    visibleTreeCount = 0;
    bestVisibility = -1;
    isVisibleFromOutside = new boolean[rowCount][columnCount];
  }

  private void calculateVisibility(int row, int column) {
    int visibilityUp = findViewingDistance(row, column, Direction.UP);
    int visibilityDown = findViewingDistance(row, column, Direction.DOWN);
    int visibilityLeft = findViewingDistance(row, column, Direction.LEFT);
    int visibilityRight = findViewingDistance(row, column, Direction.RIGHT);
    int visibility = visibilityUp * visibilityDown * visibilityRight * visibilityLeft;
    if (visibility > bestVisibility) {
      bestVisibility = visibility;
    }
  }

  /**
   * Look as far as the sight goes from a specific position in the forest, find
   * the viewing distance.
   *
   * @param row       The row-index of the tree to look from
   * @param column    The column-index of the tree to look from
   * @param direction The direction to look in
   * @return The viewing distance from the given tree in the given direction
   */
  private int findViewingDistance(int row, int column, Direction direction) {
    int viewDistance = 0;
    boolean foundTallerTree = false;
    int startingTreeHeight = getTreeHeight(row, column);

    Sight sight = Sight.fromDirection(direction, row, column, rowCount, columnCount);
    sight.advanceOneStep();

    while (!foundTallerTree && !sight.isBoundaryReached()) {
      ++viewDistance;
      int heightOfTreeInSight = treeHeights.getValueAt(sight.getCurrentRow(),
          sight.getCurrentColumn());
      if (heightOfTreeInSight >= startingTreeHeight) {
        foundTallerTree = true;
      }
      sight.advanceOneStep();
    }

    if (!foundTallerTree) {
      markAsVisibleFromOutside(row, column);
    }

    return viewDistance;
  }

  private void markAsVisibleFromOutside(int row, int column) {
    if (!isVisibleFromOutside[row][column]) {
      isVisibleFromOutside[row][column] = true;
      visibleTreeCount++;
    }
  }

  private int getTreeHeight(int row, int column) {
    return treeHeights.getValueAt(row, column);
  }
}
