package problem.day02;


import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 02
 * See description here: https://adventofcode.com/2022/day/2
 * Calculate scores for a set of Rock-paper-scissor games
 */
public class Solver {
  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 02...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem02.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    int score = 0;
    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        score += Game.parse(line).getScore();
      }
    }

    Logger.info("Total score: " + score);
  }
}
