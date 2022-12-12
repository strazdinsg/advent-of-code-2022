package problem.day12;

import tools.InputFile;
import tools.Logger;

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
    int pathLength = mazeSolver.findShortestPath(map.getStartPosition(), map.getEndPosition());
    Logger.info("Length of the shortest path: " + pathLength);
  }
}
