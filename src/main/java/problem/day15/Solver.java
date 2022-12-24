package problem.day15;

import java.util.LinkedList;
import java.util.List;
import tools.InputFile;
import tools.IntegerRange;
import tools.Logger;
import tools.NonOverlappingRanges;
import tools.Vector;

/**
 * Solution for the problem of Day 15
 * See description here: https://adventofcode.com/2022/day/15
 * For a specific row find the number of cells where no beacon is located.
 * Idea: For each sensor we can detect the "projection" of the safe area on the given row, where
 * no beacon is located for sure. Let's call these projections "Clean lines". Each Clean line
 * consists of one or several horizontally aligned cells. Then we merge the Clean lines (cut the
 * overlapping cells). The answer for part 1 is the sum of cells inside the merged Clean lines.
 * If there would be very many sensors, we could first sort the Clean lines, but there will be less
 * than 30 Clean lines, we can do a full-search for finding the overlaps (O(n^2))
 * Also: we need to remove the beacons from the clean lines.
 */
public class Solver {
  //  private static final int INTERESTING_ROW_INDEX = 2000000;
  private static final int INTERESTING_ROW_INDEX = 10;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 15...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem15.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    List<Sensor> sensors = new LinkedList<>();

    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        sensors.add(new Sensor(line));
      }
    }

    NonOverlappingRanges cleanLines = new NonOverlappingRanges();
    for (Sensor sensor : sensors) {
      IntegerRange cleanLine = sensor.findCleanLine(INTERESTING_ROW_INDEX);
      if (cleanLine != null) {
        cleanLines.add(cleanLine);
      }
    }

    for (Sensor sensor : sensors) {
      Vector beaconPosition = sensor.getClosestBeaconPosition();
      if (beaconPosition.getY() == INTERESTING_ROW_INDEX) {
        cleanLines.removeSingleValue(beaconPosition.getX());
      }
    }

    Logger.info(cleanLines.getLengthSum() + "");
  }
}
