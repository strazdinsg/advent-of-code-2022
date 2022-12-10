package problem.day09;

import tools.Direction;
import tools.Vector;

/**
 * Movement of a rope - specific number of positions in a specific direction.
 */
public class Movement {
  Vector movement;

  /**
   * Creates a rope movement from a string. The string is expected to be in the format C N, where:
   * - C is a command. Only the following values are valid: U, R, D, L.
   * There represent movement Up, Right, Down, Left.
   * - N is an integer which specifies how many positions in the given direction the
   * movement happened.
   *
   * @param movementString The movement command as a string
   * @throws IllegalArgumentException When the provided movementString is invalid.
   *                                  Note: null is a valid movement string and
   *                                  corresponds to no movement!
   */
  public Movement(String movementString) throws IllegalArgumentException {
    if (movementString != null) {
      Direction direction = parseDirection(movementString);
      parseSpace(movementString);
      int length = parseLength(movementString);
      setDeltas(length, direction);
    }
  }

  private void setDeltas(int length, Direction direction) {
    int deltaX = 0;
    int deltaY = 0;
    switch (direction) {
      case UP -> deltaY = -length;
      case DOWN -> deltaY = length;
      case LEFT -> deltaX = -length;
      case RIGHT -> deltaX = length;
    }
    movement = new Vector(deltaX, deltaY);
  }

  private Direction parseDirection(String movementString) {
    if (movementString.length() < 1) {
      throw new IllegalArgumentException("Invalid command: " + movementString);
    }

    return switch (movementString.charAt(0)) {
      case 'U' -> Direction.UP;
      case 'R' -> Direction.RIGHT;
      case 'D' -> Direction.DOWN;
      case 'L' -> Direction.LEFT;
      default -> throw new IllegalArgumentException("Invalid command: " + movementString);
    };
  }

  private void parseSpace(String movementString) throws IllegalArgumentException {
    if (movementString.length() < 2 || movementString.charAt(1) != ' ') {
      throw new IllegalArgumentException("Movement command must contain space. Invalid command: "
          + movementString);
    }
  }

  private int parseLength(String movementString) {
    if (movementString.length() < 3) {
      throw new IllegalArgumentException("Movement does not contain the length: " + movementString);
    }

    String lengthString = movementString.substring(2);
    try {
      return Integer.parseInt(lengthString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid movement length: " + movementString);
    }
  }

  /**
   * Returns true as long as there is "some unfinished motion" left.
   *
   * @return True when motion is not finished, false when it is finished.
   */
  public boolean isNotFinished() {
    return !movement.isZero();
  }

  /**
   * Take one step in the movement direction (one position), reduce the movement accordingly.
   *
   * @return The vector representing one step (one position) in the movement direction
   */
  public Vector takeOneStep() {
    Vector step = movement.scaleToOneUnit();
    movement = movement.minus(step);
    return step;
  }
}
