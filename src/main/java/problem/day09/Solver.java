package problem.day09;


import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 09
 * See description here: https://adventofcode.com/2022/day/9
 * Track movement of the tail of the rope when head of the rope is moving.
 * Idea: move the head step by step. In every step check the tail's position relative to the head:
 * - If it is not further than one cell away on any dimension, do nothing
 * - If it is two cells away on dimension D1
 * - when zero cells away on dimension D2, move one step closer to head in dimension D1
 * - when one cell away on dimension D2, move diagonally so that it is next to head on dimension D1
 * Track the motion of the tail of the rope over an infinite field
 */
public class Solver {


  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 09...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem09.input");
    if (!inputFile.exists()) {
      return;
    }

    InfiniteRopeField field = new InfiniteRopeField();
    Rope rope = new Rope(field);

    while (!inputFile.isEndOfFile()) {
      String command = inputFile.readLine();
      if (command != null) {
        Logger.info("> " + command);
        Movement movement = new Movement(command);
        rope.move(movement);
      }
    }

    Logger.info("The tail has moved over " + field.getUniqueVisitedPositionCount()
        + " unique positions");
  }
}
