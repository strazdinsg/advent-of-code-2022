package problem.day04;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 04
 * See description here: https://adventofcode.com/2022/day/4
 * Find overlapping intervals
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 04...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem04.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    int containedIntervalCount = 0;
    int overlappingIntervalCount = 0;
    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        IntervalPair intervals = IntervalPair.createFromString(line);
        if (intervals.oneContainsOther()) {
          containedIntervalCount++;
        }
        if (intervals.overlap()) {
          overlappingIntervalCount++;
        }
      }
    }
    Logger.info("Contained interval count: " + containedIntervalCount);
    Logger.info("Overlapping interval count: " + overlappingIntervalCount);
  }
}
