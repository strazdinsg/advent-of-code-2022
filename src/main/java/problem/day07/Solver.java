package problem.day07;

import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 07.
 * See description here: https://adventofcode.com/2022/day/7
 * Read command-line outputs, parse directories and file sizes. Find directories of specific size
 */
public class Solver {
  private static final int MAX_DIR_SIZE = 100000;
  private static final char COMMAND_SYMBOL = '$';
  private static final long TOTAL_DISK_SPACE = 70000000;
  private static final long DESIRED_FREE_SPACE = 30000000;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 07...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem07.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    FileSystem fileSystem = new FileSystem();
    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        if (isCommand(line)) {
          fileSystem.execute(new Command(line));
        } else {
          fileSystem.addCurrentDirContent(line);
        }
      }
    }

    Logger.info("Sum of directories not exceeding " + MAX_DIR_SIZE + ": "
        + fileSystem.countSumOfDirectoriesNotExceeding(MAX_DIR_SIZE));

    long totalDirectorySize = fileSystem.getTotalSize();
    Logger.info("Total File system size: " + totalDirectorySize);
    long freeSpace = TOTAL_DISK_SPACE - totalDirectorySize;
    long minSizeToRemove = DESIRED_FREE_SPACE - freeSpace;
    Logger.info("Need to remove at least " + minSizeToRemove + " bytes");
    Logger.info("Size of smallest dir to remove: "
        + fileSystem.getSmallestDirExceeding(minSizeToRemove));
  }

  private boolean isCommand(String line) {
    return line.charAt(0) == COMMAND_SYMBOL;
  }
}
