package problem.day13;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 13
 * See description here: https://adventofcode.com/2022/day/13
 * Parse lists of integers (which can contain sub-lists), compare them.
 * Idea: Parse the lists recursively. Then compare them recursively.
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 13...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem13.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    int sum = 0;
    int index = 0;
    while (!inputFile.isEndOfFile()) {
      ListOrInt left = readList(inputFile);
      ListOrInt right = readList(inputFile);
      inputFile.skipEmptyLine();

      if (left != null && right != null) {
        index++;
        if (ListOrInt.isRightOrder(left, right)) {
          sum += index;
        }
      }
    }

    Logger.info("Sum of indices: " + sum);
  }

  private ListOrInt readList(InputFile inputFile) {
    String line = inputFile.readLine();
    return line != null ? new ListOrInt(line) : null;
  }
}
