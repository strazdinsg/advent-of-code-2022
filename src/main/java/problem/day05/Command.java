package problem.day05;

/**
 * Command for the crane for moving crates between two stacks.
 */
public class Command {
  private final int count;
  private final int fromIndex;
  private final int toIndex;

  /**
   * Create a new command from a string.
   *
   * @param commandString Command string from the input file
   * @throws IllegalArgumentException When the command string is not in the expected format
   */
  public Command(String commandString) throws IllegalArgumentException {
    String[] parts = commandString.split(" ");
    expect(parts[0], "move");
    expect(parts[2], "from");
    expect(parts[4], "to");
    count = parseInt(parts[1]);
    fromIndex = parseInt(parts[3]);
    toIndex = parseInt(parts[5]);
  }

  private void expect(String s, String expectedValue) throws IllegalArgumentException {
    if (!expectedValue.equals(s)) {
      throw new IllegalStateException("Illegal command format. Expected "
          + expectedValue + ", got " + s);
    }
  }

  private int parseInt(String s) {
    try {
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      throw new IllegalStateException("Invalid command format. Expected an integer, got " + s);
    }
  }

  /**
   * Get the number of crates to be moved.
   *
   * @return The number of crates to be moved
   */
  public int getCount() {
    return count;
  }

  /**
   * Get the index of stack to take the crates from.
   *
   * @return The source stack index. Warning: indexing starts at 1.
   */
  public int getFromIndex() {
    return fromIndex;
  }

  /**
   * Get the index of stack to move the crates to.
   *
   * @return The destination stack index. Warning: indexing starts at 1.
   */
  public int getToIndex() {
    return toIndex;
  }
}
