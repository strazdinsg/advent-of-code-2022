package problem.day04;

/**
 * Contains information about two integer intervals.
 */
public class IntervalPair {
  private final Interval first;
  private final Interval second;

  private IntervalPair(Interval first, Interval second) {
    this.first = first;
    this.second = second;
  }


  /**
   * Create intervals from a string representing two intervals.
   *
   * @param line The line representing the intervals
   * @return The interval pair
   */
  public static IntervalPair createFromString(String line) {
    String[] intervalStrings = line.split(",");
    if (intervalStrings.length != 2) {
      throw new IllegalArgumentException("Invalid interval string: " + line);
    }
    Interval first = Interval.createFromString(intervalStrings[0]);
    Interval second = Interval.createFromString(intervalStrings[1]);

    return new IntervalPair(first, second);
  }

  /**
   * Checks whether one interval contains another (x contains y or y contains x).
   *
   * @return True if one interval contains the other, false otherwise
   */
  public boolean oneContainsOther() {
    return first.contains(second) || second.contains(first);
  }

  /**
   * Checks whether the two intervals overlap.
   *
   * @return True if the intervals overlap, false otherwise.
   */
  public boolean overlap() {
    return first.overlaps(second);
  }

  @Override
  public String toString() {
    return "Pair { " + first + ", " + second + '}';
  }

}
