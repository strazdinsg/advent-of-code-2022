package problem.day11.operation;

/**
 * An operation which multiplies a given worry-level with a constant integer.
 */
public class MultiplyOperation implements Operation {
  private final int multiplier;

  public MultiplyOperation(int multiplier) {
    this.multiplier = multiplier;
  }

  @Override
  public long perform(long worryLevel) {
    return worryLevel * multiplier;
  }

  @Override
  public String toString() {
    return "* " + multiplier;
  }
}
