package problem.day11;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 11
 * See description here: https://adventofcode.com/2022/day/11
 * Emulate monkeys throwing items according to a specific algorithm.
 * The idea for part 2: all test conditions are checks for divisibility. If we know that all
 * monkeys check for divisibility with integers d1, d2, ..., dN, then we can stop worrying about
 * big numbers and "normalize" all the worry levels at the end of each round: take worry level w
 * and replace it with w % (d1 * d2 * ... * dN)
 */
public class Solver {
  private static final int MONKEY_COUNT = 8;
  private static final int SIMULATION_ROUNDS = 10000;

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
    monkeys.updateLeastCommonMultiple();

    for (int i = 0; i < SIMULATION_ROUNDS; ++i) {
      monkeys.simulateRound();
    }

    monkeys.printMonkeyStats();
    Logger.info("Level of monkey business: " + monkeys.getBusinessScore());
  }
}
