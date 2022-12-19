package problem.day11;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 11
 * See description here: https://adventofcode.com/2022/day/11
 * Emulate monkeys throwing items according to a specific algorithm
 */
public class Solver {
  private static final int MONKEY_COUNT = 8;
  private static final int SIMULATION_ROUNDS = 20;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 11...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem11.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    MonkeyCrowd monkeys = new MonkeyCrowd();
    for (int i = 0; i < MONKEY_COUNT; ++i) {
      Monkey monkey = MonkeyFileParser.parse(inputFile, i);
      monkeys.add(monkey);
    }

    for (int i = 0; i < SIMULATION_ROUNDS; ++i) {
      monkeys.simulateRound();
    }

    Logger.info("Level of monkey business: " + monkeys.getBusinessScore());
  }


}
