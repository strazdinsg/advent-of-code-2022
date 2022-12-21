package problem.day14;

import tools.Logger;
import tools.Rectangle;
import tools.StringGrid;
import tools.Vector;

/**
 * A "horizontal map" representing the walls as a grid. Each cell can either be occupied or free.
 */
public class WallMap {
  private static final char EMPTY_CELL = '.';
  private static final char WALL_CELL = '#';
  private static final char SAND_CELL = 'o';

  private Rectangle boundaries;
  private final Vector sandSource;
  private boolean overflow;
  private StringGrid grid;

  /**
   * Create a wall map.
   *
   * @param boundaries The boundary coordinates. The map will be shifted based on
   *                   these boundaries and the sand overflow will be detected based on these.
   * @param sandSource The source where each new grain of sand will be dropped from.
   */
  public WallMap(Rectangle boundaries, Vector sandSource) {
    this.boundaries = boundaries;
    this.sandSource = adjustSandSourceForBoundaries(sandSource);
    Logger.info("Sand source: " + this.sandSource);
    overflow = false;
    createEmptyCells();
  }

  private Vector adjustSandSourceForBoundaries(Vector sandSource) {
    int y = Math.max(sandSource.getY(), boundaries.getTopLeft().getY() - 1);
    return shiftCoordinatesByBoundaries(sandSource.getX(), y);
  }

  private void createEmptyCells() {
    int rowCount = boundaries.getHeight();
    int columnCount = boundaries.getWidth();
    String emptyRow = ("" + EMPTY_CELL).repeat(columnCount);

    grid = new StringGrid();
    for (int i = 0; i < rowCount; ++i) {
      grid.appendRow(emptyRow);
    }
  }

  /**
   * Draw a new path on the map.
   *
   * @param path The path to draw. The cells covered by the path will be marked as occupied.
   */
  public void drawPath(Path path) {
    Vector from = null;
    for (Vector to : path.getCorners()) {
      if (from != null) {
        drawLine(from, to);
      }
      from = to;
    }
  }

  private void drawLine(Vector from, Vector to) {
    Logger.info("Draw line " + from + " - " + to);
    int horizontalStep = to.getX() - from.getX();
    int verticalStep = to.getY() - from.getY();
    // make sure the steps are either +1 or -1, or 0
    if (horizontalStep != 0) {
      horizontalStep /= Math.abs(horizontalStep);
    }
    if (verticalStep != 0) {
      verticalStep /= Math.abs(verticalStep);
    }

    Vector cursor = from;
    boolean drawing = true;
    while (drawing) {
      markCellAsWall(cursor.getX(), cursor.getY());
      if (cursor.equals(to)) {
        drawing = false;
      } else {
        cursor = cursor.plus(horizontalStep, verticalStep);
      }
    }
  }

  private void markCellAsWall(int x, int y) {
    Vector shifted = shiftCoordinatesByBoundaries(x, y);
    grid.replaceCharacter(shifted.getY(), shifted.getX(), WALL_CELL);
  }

  /**
   * Shift the coordinates to the "boundary coordinate system". I.e., if the top-left corner for
   * the boundaries is (20, 30), subtract (20, 30) from the given (x, y).
   *
   * @param x The x-coordinate to shift
   * @param y The y-coordinate to shift
   * @return New coordinates, shifted by (x, y)
   */
  private Vector shiftCoordinatesByBoundaries(int x, int y) {
    return new Vector(x - boundaries.getTopLeft().getX(), y - boundaries.getTopLeft().getY());
  }

  /**
   * Check whether all the sand is standing still, without overflow.
   *
   * @return True when the sand is standing still; false if there is an overflow of sand.
   */
  public boolean isSandStandingStill() {
    return !overflow;
  }

  /**
   * Drop yet another grain of sand from the source.
   */
  public void dropOneGrain() {
    boolean dropping = true;
    Vector grainPosition = sandSource;
    while (dropping) {
      if (hasSandOverflown(grainPosition)) {
        overflow = true;
        dropping = false;
      } else if (canDropStraightDown(grainPosition)) {
        grainPosition = grainPosition.plus(0, 1);
      } else if (canDropToTheLeft(grainPosition)) {
        grainPosition = grainPosition.plus(-1, 1);
      } else if (canDropToTheRight(grainPosition)) {
        grainPosition = grainPosition.plus(1, 1);
      } else {
        dropping = false;
      }
    }

    if (!overflow) {
      markCellAsSand(grainPosition);
    }
  }

  private boolean hasSandOverflown(Vector grainPosition) {
    return grainPosition.getX() < boundaries.getTopLeft().getX()
        || grainPosition.getX() > boundaries.getBottomRight().getX()
        || grainPosition.getY() > boundaries.getBottomRight().getY();
  }

  private boolean canDropStraightDown(Vector grainPosition) {
    return isAtBottomRow(grainPosition) || isCellEmpty(grainPosition.plus(0, 1));
  }

  private boolean isAtBottomRow(Vector grainPosition) {
    return grainPosition.getY() == boundaries.getBottomRight().getY();
  }

  private boolean canDropToTheLeft(Vector grainPosition) {
    return isAtBottomRow(grainPosition) || isAtLeftBoundary(grainPosition)
        || isCellEmpty(grainPosition.plus(-1, 1));
  }

  private boolean isAtLeftBoundary(Vector grainPosition) {
    return grainPosition.getX() == boundaries.getTopLeft().getX();
  }

  private boolean canDropToTheRight(Vector grainPosition) {
    return isAtBottomRow(grainPosition) || isAtRightBoundary(grainPosition)
        || isCellEmpty(grainPosition.plus(1, 1));
  }

  private boolean isAtRightBoundary(Vector grainPosition) {
    return grainPosition.getX() == boundaries.getBottomRight().getX();
  }

  private boolean isCellEmpty(Vector position) {
    char cell = grid.getCharacter(position.getY(), position.getX());
    return cell == EMPTY_CELL;
  }

  private void markCellAsSand(Vector grainPosition) {
    grid.replaceCharacter(grainPosition.getY(), grainPosition.getX(), SAND_CELL);
  }

  /**
   * Print a human-readable version of the map on console.
   * Each wall is represented by #, grains of sand are represented by o and empty cells by .
   */
  public void debugPrint() {
    for (int rowIndex = 0; rowIndex < grid.getRowCount(); ++rowIndex) {
      Logger.info(grid.getRow(rowIndex));
    }
  }

  /**
   * Shift the boundaries so that the top-left corner is at (0, 0).
   */
  public void normalizeBoundaries() {
    boundaries = boundaries.minus(boundaries.getTopLeft());
  }
}
