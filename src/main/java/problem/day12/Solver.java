package problem.day12;

import tools.InputFile;
import tools.Logger;
import tools.Vector;

/**
 * Solution for the problem of Day 12
 * See description here: https://adventofcode.com/2022/day/12
 * Find the shortest path in a topological map.
 * Idea: Search the shortest path in a graph, use breadth-first search.
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 12...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem12.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    TopoMap map = new TopoMap();
    map.initializeFrom(inputFile.readAllIntoGridBuffer());
    MazeSolver mazeSolver = new MazeSolver(map);
    Logger.info("Length of the shortest path from the starting position: "
        + mazeSolver.findShortestPath(map.getStartPosition(), map.getEndPosition()));

    int shortestDistance = Integer.MAX_VALUE;
    for (int row = 0; row < map.getHeight(); ++row) {
      for (int column = 0; column < map.getWidth(); ++column) {
        if (map.isGroundLevel(row, column)) {
          Vector start = new Vector(column, row);
          int distance = mazeSolver.findShortestPath(start, map.getEndPosition());
          if (distance != MazeSolver.NOT_REACHED) {
            shortestDistance = Math.min(distance, shortestDistance);
          }
        }
      }
    }
    Logger.info("Shortest distance of all possible hikes: " + shortestDistance);
  }
}
