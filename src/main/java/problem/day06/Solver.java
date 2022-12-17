package problem.day06;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 06
 * See description here: https://adventofcode.com/2022/day/6
 * Read characters, detect the first position where the last 4 characters are all different
 */
public class Solver {
  private static final int START_SIGNAL_LENGTH = 4;
  private static final int MESSAGE_START_LENGTH = 14;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 06...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem06.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    Integer startPosition = null;
    StartSignalDetector detector = new StartSignalDetector(MESSAGE_START_LENGTH);

    while (!inputFile.isEndOfFile() && startPosition == null) {
      Character c = inputFile.readOneChar();
      if (c != null) {
        detector.add(c);
      }
      if (detector.isDetected()) {
        startPosition = detector.getProcessedCharacterCount();
      }
    }

    Logger.info("Message start after first " + startPosition + " symbols");
  }
}
