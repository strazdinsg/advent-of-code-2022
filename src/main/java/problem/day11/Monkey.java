package problem.day11;

import java.util.List;
import problem.day11.operation.Operation;
import tools.Logger;

/**
 * A monkey throwing stuff according to a specific algorithm.
 */
public class Monkey {
  private static final long BOREDOM_DIVIDER = 1L;

  private final List<Long> itemWorries;
  private final Operation operation;
  private final DivisionCondition testCondition;
  private final ThrowActions actions;

  private long inspectionCount;
  private int leastCommonMultiplier;

  /**
   * Create a new monkey.
   *
   * @param items         Initial items the monkey possesses
   * @param operation     The operation on worry-score the monkey performs in each turn
   * @param testCondition The test condition the monkey performs in each step
   * @param actions       The possible actions - to which monkeys the items are thrown
   */
  public Monkey(List<Long> items, Operation operation, DivisionCondition testCondition,
                ThrowActions actions) {
    this.itemWorries = items;
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

  private long getFirstItemWorryLevel() {
    return itemWorries.iterator().next();
  }

  /**
   * Add an item to the monkey item list (thrown to this monkey by another monkey).
   *
   * @param worryLevel The current worry-level of the received item
   */
  public void addItem(long worryLevel) {
    itemWorries.add(worryLevel);
  }

  @Override
  public String toString() {
    return itemWorries.toString() + ", inspectionCount: " + inspectionCount;
  }

  public boolean hasItems() {
    return !itemWorries.isEmpty();
  }

  public void removeFirstItem() {
    itemWorries.remove(0);
  }

  /**
   * Do recalculation of the worry-level for the first item in the list.
   *
   * @return The updated worry-level of the first item.
   */
  public long updateWorryLevelForFirstItem() {
    long worryLevel = getFirstItemWorryLevel();
    worryLevel = operation.perform(worryLevel);
    worryLevel = decreaseWorry(worryLevel);
    setFirstItemWorryLevel(worryLevel);
    inspectionCount++;
    return worryLevel;
  }

  private long decreaseWorry(long worryLevel) {
    if (isFirstPartOfTask()) {
      worryLevel = decreaseWorryByDivision(worryLevel);
    } else {
      worryLevel = keepRemainderOfMegaDivision(worryLevel);
    }
    return worryLevel;
  }

  private long keepRemainderOfMegaDivision(long worryLevel) {
    return worryLevel % leastCommonMultiplier;
  }

  private long decreaseWorryByDivision(long worryLevel) {
    return worryLevel / BOREDOM_DIVIDER;
  }

  private boolean isFirstPartOfTask() {
    return BOREDOM_DIVIDER > 1;
  }

  private void setFirstItemWorryLevel(long worryLevel) {
    itemWorries.set(0, worryLevel);
  }

  /**
   * Get the number of times this monkey has inspected an item.
   *
   * @return The total inspection count
   */
  public long getInspectionCount() {
    return inspectionCount;
  }

  /**
   * Set the mega-divider: the least common multiplier (LCM) for all monkeys. This can be used to
   * decrease the growing worry in each turn. Instead of keeping the high value, we can simply
   * Keep the worry's remainder from division by this LCM.
   *
   * @param leastCommonMultiplier d1 * d2 * ... * dN (see idea in Solver)
   */
  public void setLeastCommonMultiplier(int leastCommonMultiplier) {
    this.leastCommonMultiplier = leastCommonMultiplier;
  }

  public long getTestDivider() {
    return testCondition.getDivider();
  }
}
