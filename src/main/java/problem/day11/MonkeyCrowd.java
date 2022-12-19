package problem.day11;

import java.util.ArrayList;
import java.util.List;
import tools.Logger;

/**
 * A crowd of monkeys.
 */
public class MonkeyCrowd {
  private final List<Monkey> monkeys = new ArrayList<>();

  /**
   * Add a new monkey to the crowd.
   *
   * @param monkey The monkey to add
   */
  public void add(Monkey monkey) {
    monkeys.add(monkey);
  }

  /**
   * Simulate one round of item throwing.
   */
  public void simulateRound() {
    for (int i = 0; i < monkeys.size(); ++i) {
      Monkey monkey = monkeys.get(i);
      while (monkey.hasItems()) {
        long newWorryLevel = monkey.updateWorryLevelForFirstItem();
        int destinationIndex = monkey.getFirstItemDestination();
        monkey.removeFirstItem();
        monkeys.get(destinationIndex).addItem(newWorryLevel);
      }
    }
  }

  /**
   * Print monkey stats, for debugging.
   */
  public void printMonkeyStats() {
    Logger.info("========================================");
    for (int i = 0; i < monkeys.size(); ++i) {
      Logger.info("Monkey " + i + ": " + monkeys.get(i));
    }
    Logger.info("========================================");
  }

  /**
   * Get monkey-business score, according to the logic of Day11-part 1.
   *
   * @return The monkey business score (multiplication of two highest values of
   *     item-inspection-count-per-monkey)
   */
  public long getBusinessScore() {
    int maxMonkeyIndex = findMaxInspectionsSmallerThan(Integer.MAX_VALUE);
    long maxInspections = monkeys.get(maxMonkeyIndex).getInspectionCount();
    int secondMaxMonkeyIndex = findMaxInspectionsSmallerThan(maxInspections);
    long secondMaxInspections = monkeys.get(secondMaxMonkeyIndex).getInspectionCount();
    return maxInspections * secondMaxInspections;
  }

  private int findMaxInspectionsSmallerThan(long threshold) {
    int maxIndex = -1;
    long maxValue = -1;
    for (int i = 0; i < monkeys.size(); ++i) {
      long inspectionCount = monkeys.get(i).getInspectionCount();
      if (inspectionCount > maxValue && inspectionCount < threshold) {
        maxValue = inspectionCount;
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  /**
   * Set the mega-divider for all monkeys: the least common multiple.
   */
  public void updateLeastCommonMultiple() {
    int lcm = 1;
    for (Monkey m : monkeys) {
      lcm *= m.getTestDivider();
    }
    Logger.info("LCM = " + lcm);

    for (Monkey m : monkeys) {
      m.setLeastCommonMultiplier(lcm);
    }
  }
}
