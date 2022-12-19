package problem.day11;

/**
 * Represents an if-divisible-by-x test that a monkey is checking for each item on each turn.
 */
public class DivisionCondition {
  private final int divider;

  public DivisionCondition(int divider) {
    this.divider = divider;
  }

  public boolean isTrueFor(int worryLevel) {
    return worryLevel % divider == 0;
  }

  @Override
  public String toString() {
    return "% " + divider;
  }

  public int getDivider() {
    return divider;
  }
}
