package problem.day05;

import java.util.List;
import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 05
 * See description here: https://adventofcode.com/2022/day/5
 * Track movement of blocks in stacks
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 05...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem05.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    List<String> stackLines = inputFile.readLinesUntilEmptyLine();
    CrateStacks stacks = new CrateStacks(stackLines);
    Logger.info("Top crates in the beginning: " + stacks.getTopCrates());

    while (!inputFile.isEndOfFile()) {
      String commandString = inputFile.readLine();
      if (commandString != null) {
        stacks.move(new Command(commandString));
      }
    }

    Logger.info("Top crates: " + stacks.getTopCrates());
  }
}
