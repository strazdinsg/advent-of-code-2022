package problem.day11.operation;

/**
 * An operation which adds a specific increment to a worry-level.
 */
public class IncrementOperation implements Operation {
  private final int increment;

  public IncrementOperation(int increment) {
    this.increment = increment;
  }

  @Override
  public long perform(long worryLevel) {
    return worryLevel + increment;
  }

  @Override
  public String toString() {
    return "+ " + increment;
  }
}
