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
      String[] groupRucksacks = readGroupRucksacks(inputFile);
      if (groupRucksacks.length > 0) {
        Character groupBadge = DuplicateFinder.findDuplicateChar(groupRucksacks);
        prioritySum += DuplicateFinder.getPriority(groupBadge);
      }
    }
    Logger.info("Priority sum: " + prioritySum);
  }

  private String[] readGroupRucksacks(InputFile inputFile) {
    String line1 = inputFile.readLine();
    if (line1 == null) {
      return new String[]{};
    }

    String line2 = inputFile.readLine();
    String line3 = inputFile.readLine();
    return new String[]{line1, line2, line3};
  }
}
