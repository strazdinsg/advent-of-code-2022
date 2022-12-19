package problem.day11;

import java.util.List;
import problem.day11.operation.Operation;
import tools.Logger;

/**
 * A monkey throwing stuff according to a specific algorithm.
 */
public class Monkey {
  private static final Integer BOREDOM_DIVIDER = 3;

  private final List<Integer> items;
  private final Operation operation;
  private final DivisionCondition testCondition;
  private final ThrowActions actions;

  private int inspectionCount;

  /**
   * Create a new monkey.
   *
   * @param items         Initial items the monkey possesses
   * @param operation     The operation on worry-score the monkey performs in each turn
   * @param testCondition The test condition the monkey performs in each step
   * @param actions       The possible actions - to which monkeys the items are thrown
   */
  public Monkey(List<Integer> items, Operation operation, DivisionCondition testCondition,
                ThrowActions actions) {
    this.items = items;
    this.operation = operation;
    this.testCondition = testCondition;
    this.actions = actions;
    inspectionCount = 0;
  }

  /**
   * Get the destination of the first item of the monkey - to which other monkey it will be thrown.
   *
   * @return Index of the monkey to which this monkey needs to throw the item.
   */
  public int getFirstItemDestination() {
    return actions.getDestination(testCondition.isTrueFor(getFirstItemWorryLevel()));
  }

  private int getFirstItemWorryLevel() {
    return items.iterator().next();
  }

  /**
   * Add an item to the monkey item list (thrown to this monkey by another monkey).
   *
   * @param worryLevel The current worry-level of the received item
   */
  public void addItem(Integer worryLevel) {
    items.add(worryLevel);
  }

  @Override
  public String toString() {
    return items.toString() + ", inspectionCount: " + inspectionCount;
  }

  public boolean hasItems() {
    return !items.isEmpty();
  }

  public void removeFirstItem() {
    items.remove(0);
  }

  /**
   * Do recalculation of the worry-level for the first item in the list.
   *
   * @return The updated worry-level of the first item.
   */
  public int updateWorryLevelForFirstItem() {
    int worryLevel = getFirstItemWorryLevel();
    worryLevel = operation.perform(worryLevel) / BOREDOM_DIVIDER;
    setFirstItemWorryLevel(worryLevel);
    inspectionCount++;
    return worryLevel;
  }

  private void setFirstItemWorryLevel(int worryLevel) {
    items.set(0, worryLevel);
  }

  /**
   * Get the number of times this monkey has inspected an item.
   *
   * @return The total inspection count
   */
  public int getInspectionCount() {
    return inspectionCount;
  }
}
