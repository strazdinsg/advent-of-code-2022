package problem.day02;

/**
 * Represents result of a Rock-Paper-Scissors game (from the first person's perspective).
 */
public enum GameResultType {
  WIN, LOSE, DRAW;

  /**
   * Parse a character as a Game result type.
   *
   * @param c The character representing game result
   * @return The corresponding GameResultType enum
   */
  public static GameResultType from(char c) {
    return switch (c) {
      case 'X' -> LOSE;
      case 'Y' -> DRAW;
      case 'Z' -> WIN;
      default -> throw new IllegalArgumentException("Unexpected game result character: " + c);
    };
  }
}
