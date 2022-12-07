package problem.day01;

import tools.InputFile;
import tools.IntegerOrEmpty;
import tools.Logger;

/**
 * Solution for the problem of Day 01
 * See description here: https://adventofcode.com/2022/day/1
 * The main idea: sum up numbers in chunks (total number of calories in a backpack for an elf),
 * find the highest sum among all chunks.
 * Chunks are split by an empty line.
 */
public class Solver {
  private InputFile inputFile;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Starting...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    inputFile = new InputFile("problem01.input");
    if (!inputFile.exists()) {
      return;
    }

    long maxCalories = 0;
    Long calories = getCaloriesForNextElf();

    while (calories != null) {
      if (calories > maxCalories) {
        maxCalories = calories;
      }
      calories = getCaloriesForNextElf();
    }

    Logger.info("The elves will start eating from the backpack uf the unlucky one who has "
        + maxCalories + " calories in his backpack");
  }

  /**
   * Read the next chunk of numbers (calories for the next elf), sum up the values.
   *
   * @return The sum of calories for the next elf or null if end-of-file is reached
   */
  private Long getCaloriesForNextElf() {
    IntegerOrEmpty n = inputFile.readLineAsInteger();
    if (inputFile.isEndOfFile()) {
      return null;
    }

    Long sum = 0L;
    while (n.isNumber()) {
      sum += n.getValue();
      n = inputFile.readLineAsInteger();
    }
    return sum;
  }
}
