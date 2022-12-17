package problem.day04;

/**
 * Represents an interval of integers.
 */
public class Interval {
  final int from;
  final int to;

  private Interval(int from, int to) {
    this.from = from;
    this.to = to;
  }

  /**
   * Create an interval from a string in format from-to.
   *
   * @param intervalString The string representing the interval
   * @return The interval
   * @throws IllegalStateException when the intervalString is not in the expected format
   */
  public static Interval createFromString(String intervalString) throws IllegalArgumentException {
    String[] parts = intervalString.split("-");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid interval string: " + intervalString);
    }

    try {
      int from = Integer.parseInt(parts[0]);
      int to = Integer.parseInt(parts[1]);
      return new Interval(from, to);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid interval: " + intervalString);
    }
  }

  /**
   * Checks whether this interval overlaps with a second interval.
   *
   * @param second The second interval to check the overlap with
   * @return True if the two intervals fully or partly overlap, false otherwise.
   */
  public boolean overlaps(Interval second) {
    return this.startsWithinSecond(second)
        || this.endsWithinSecond(second)
        || this.contains(second);
  }

  /**
   * Checks whether this interval fully contains the second interval.
   *
   * @param second The second interval
   * @return True if this contains the second interval fully, false otherwise
   */
  public boolean contains(Interval second) {
    return this.from <= second.from && this.to >= second.to;
  }

  @Override
  public String toString() {
    return from + "-" + to;
  }

  private boolean startsWithinSecond(Interval second) {
    return this.from >= second.from && this.from <= second.to;
  }

  private boolean endsWithinSecond(Interval second) {
    return this.to >= second.from && this.to <= second.to;
  }
}
