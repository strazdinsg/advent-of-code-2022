package problem.day14;

import java.util.LinkedList;
import java.util.List;
import tools.InputFile;
import tools.Logger;
import tools.Rectangle;
import tools.Vector;

/**
 * Solution for the problem of Day 14
 * See description here: https://adventofcode.com/2022/day/14
 * Build a horizontal map of walls, then drop grains of sand inside the walls until it starts
 * to spill over.
 */
public class Solver {
  private static final Vector SAND_SOURCE_COORDINATES = new Vector(500, 0);

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 14...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem14.input");
    if (!inputFile.exists()) {
      Logger.info("Input file not found");
      return;
    }

    List<Path> paths = readLinesFrom(inputFile);
    Rectangle boundaries = findMapBoundaries(paths);
    Logger.info("Boundaries: " + boundaries);
    WallMap map = new WallMap(boundaries, SAND_SOURCE_COORDINATES);

    for (Path path : paths) {
      map.drawPath(path);
    }

    map.normalizeBoundaries();

    map.debugPrint();

    int droppedGrainCount = 0;
    while (map.isSandStandingStill()) {
      map.dropOneGrain();
      Logger.info("");
      map.debugPrint();
      droppedGrainCount++;
    }

    Logger.info("Could drop " + (droppedGrainCount - 1) + " until it started to spill over");

  }

  private Rectangle findMapBoundaries(List<Path> paths) {
    Rectangle boundaries = new Rectangle(SAND_SOURCE_COORDINATES, SAND_SOURCE_COORDINATES);

    for (Path p : paths) {
      boundaries = boundaries.extend(p.getBoundaries());
    }
    return boundaries;
  }

  private List<Path> readLinesFrom(InputFile inputFile) {
    List<Path> paths = new LinkedList<>();
    while (!inputFile.isEndOfFile()) {
      String line = inputFile.readLine();
      if (line != null) {
        Path path = new Path();
        paths.add(path);
        String[] corners = line.split(" -> ");
        if (corners.length < 2) {
          throw new IllegalArgumentException("Invalid path: " + path);
        }

        for (String cornerString : corners) {
          path.addCorner(createCorner(cornerString));
        }
      }
    }

    return paths;
  }

  private Vector createCorner(String cornerString) {
    String[] coordinates = cornerString.split(",");
    if (coordinates.length != 2) {
      throw new IllegalArgumentException("Invalid corner coordinates: " + cornerString);
    }
    try {
      int x = Integer.parseInt(coordinates[0]);
      int y = Integer.parseInt(coordinates[1]);
      return new Vector(x, y);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Corner coordinates are expected to be integers: "
          + cornerString);
    }
  }

}
