package problem.day11;

/**
 * Represents actions specifying where a monkey is throwing an item in case the test condition
 * is true, and where it is throwing the item in case the test condition is false.
 */
public class ThrowActions {
  private final int trueThrowIndex;
  private final int falseThrowIndex;

  public ThrowActions(int trueThrowIndex, int falseThrowIndex) {
    this.trueThrowIndex = trueThrowIndex;
    this.falseThrowIndex = falseThrowIndex;
  }

  @Override
  public String toString() {
    return "=> " + trueThrowIndex + " | " + falseThrowIndex;
  }

  public int getDestination(boolean isConditionTrue) {
    return isConditionTrue ? trueThrowIndex : falseThrowIndex;
  }
}
