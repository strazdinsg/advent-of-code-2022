package problem.day11;

/**
 * Represents an if-divisible-by-x test that a monkey is checking for each item on each turn.
 */
public class DivisionCondition {
  private final long divider;

  public DivisionCondition(int divider) {
    this.divider = divider;
  }

  public boolean isTrueFor(long worryLevel) {
    return worryLevel % divider == 0;
  }

  public long getDivider() {
    return divider;
  }

  @Override
  public String toString() {
    return "% " + divider;
  }
}
