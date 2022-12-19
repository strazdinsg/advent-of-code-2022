package problem.day11.operation;

/**
 * An operation which squares a given worry-level.
 */
public class SquareOperation implements Operation {
  @Override
  public long perform(long worryLevel) {
    return worryLevel * worryLevel;
  }

  @Override
  public String toString() {
    return "^2";
  }
}
