package problem.day03;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 03
 * See description here: https://adventofcode.com/2022/day/3
 * Find duplicate items in two compartments of each rucksack.
 * Idea: each compartment is a set, find the character which is a duplicate.
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 03...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem03.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    int prioritySum = 0;
    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        RuckSack sack = RuckSack.createFrom(line);
        prioritySum += sack.getDuplicateItemPriority();
      }
    }
    Logger.info("Duplicate item priority sum: " + prioritySum);
  }
}
