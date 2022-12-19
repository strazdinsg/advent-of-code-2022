package problem.day11.operation;

/**
 * An arithmetic operation that a monkey is performing on each round for every item to calculate
 * the new worry-level for each item.
 */
public interface Operation {
  /**
   * Perform an operation on a given item worry-level.
   *
   * @param worryLevel The current (old) worry-level value
   * @return The new worry-level value
   */
  long perform(long worryLevel);
}
