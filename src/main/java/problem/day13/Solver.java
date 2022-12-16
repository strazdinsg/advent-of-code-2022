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

    SignalList list = new SignalList();

    while (!inputFile.isEndOfFile()) {
      list.add(readList(inputFile));
      list.add(readList(inputFile));
      inputFile.skipEmptyLine();
    }

    ListOrInt firstSpecItem = new ListOrInt("[[2]]");
    ListOrInt secondSpecItem = new ListOrInt("[[6]]");
    list.add(firstSpecItem);
    list.add(secondSpecItem);

    list.sort();

    int firstIndex = list.indexOf(firstSpecItem);
    int secondIndex = list.indexOf(secondSpecItem);
    Logger.info("Special indices: " + firstIndex + " and " + secondIndex);
    Logger.info("Answer for Part 2: " + (firstIndex * secondIndex));
  }

  private ListOrInt readList(InputFile inputFile) {
    String line = inputFile.readLine();
    return line != null ? new ListOrInt(line) : null;
  }
}
