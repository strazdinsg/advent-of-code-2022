package problem.day12;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import tools.Vector;

/**
 * A solver which finds the shortest path in a maze.
 */
public class MazeSolver {
  int[][] distances;

  private final MazeMap map;

  private boolean destinationFound;
  private Vector destination;

  public static final int NOT_REACHED = -2;
  private final Queue<Vector> cellsToVisit = new LinkedList<>();

  /**
   * Create a maze solver.
   *
   * @param map The map which is responsible for deciding which cell transitions are allowed
   *            (going from cell c1 to cell c2)
   */
  public MazeSolver(MazeMap map) {
    this.map = map;
  }

  private void clearDistances() {
    distances = new int[map.getHeight()][map.getWidth()];
    for (int i = 0; i < map.getHeight(); ++i) {
      Arrays.fill(distances[i], NOT_REACHED);
    }
  }

  /**
   * Find the shortest path between the source and the destination. Uses breadth-first search: adds
   * the current cell to a queue, then in each step takes one cell from the queue, adds all the
   * reachable neighbors to the toVisit queue, with distance+1
   *
   * @param source      Start the search from here
   * @param destination Search the shortest path to this destination
   * @return The length of the shortest path
   */
  public int findShortestPath(Vector source, Vector destination) {
    clearHistory();
    markCellAsDirectlyReachable(source);
    this.destination = destination;
    destinationFound = false;

    cellsToVisit.add(source);

    while (!cellsToVisit.isEmpty() && !destinationFound) {
      enqueueReachableNeighbors(cellsToVisit.poll());
    }

    return distances[destination.getY()][destination.getX()];
  }

  private void clearHistory() {
    clearDistances();
    cellsToVisit.clear();
  }

  private void markCellAsDirectlyReachable(Vector source) {
    distances[source.getY()][source.getX()] = 0;
  }

  /**
   * Look at all neighbors directly reachable from the current cell, add those to the
   * verticesToVisit queue, and register that these can be reached within [distance] steps.
   *
   * @param cell The current cell where the neighbors will be considered
   */
  private void enqueueReachableNeighbors(Vector cell) {
    int row = cell.getY();
    int column = cell.getX();
    int distanceToCurrentCell = distances[row][column];
    int distanceToNeighbors = distanceToCurrentCell + 1;

    if (canVisit(row, column, row - 1, column)) {
      enqueueForVisit(row - 1, column, distanceToNeighbors);
    }
    if (canVisit(row, column, row, column + 1)) {
      enqueueForVisit(row, column + 1, distanceToNeighbors);
    }
    if (canVisit(row, column, row + 1, column)) {
      enqueueForVisit(row + 1, column, distanceToNeighbors);
    }
    if (canVisit(row, column, row, column - 1)) {
      enqueueForVisit(row, column - 1, distanceToNeighbors);
    }
  }

  private boolean canVisit(int sourceRow, int sourceColumn, int destRow, int destColumn) {
    return destRow >= 0 && destRow < map.getHeight()
        && destColumn >= 0 && destColumn < map.getWidth()
        && distances[destRow][destColumn] == NOT_REACHED
        && map.canMove(sourceRow, sourceColumn, destRow, destColumn);
  }

  private void enqueueForVisit(int row, int column, int distance) {
    distances[row][column] = distance;
    Vector neighbor = new Vector(column, row);
    if (isDestination(neighbor)) {
      destinationFound = true;
    }
    cellsToVisit.add(neighbor);
  }

  private boolean isDestination(Vector neighbor) {
    return neighbor.equals(destination);
  }
}
