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
      Logger.info("Monkey " + i + ":");
      Monkey monkey = monkeys.get(i);
      while (monkey.hasItems()) {
        int newWorryLevel = monkey.updateWorryLevelForFirstItem();
        int destinationIndex = monkey.getFirstItemDestination();
        monkey.removeFirstItem();
        monkeys.get(destinationIndex).addItem(newWorryLevel);
        Logger.info("    => Item with worry level " + newWorryLevel + " is thrown to monkey "
            + destinationIndex);
      }
    }
    printMonkeyItems();
    Logger.info("");
  }

  private void printMonkeyItems() {
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
  public int getBusinessScore() {
    int maxMonkeyIndex = findMaxInspectionsSmallerThan(Integer.MAX_VALUE);
    int maxInspections = monkeys.get(maxMonkeyIndex).getInspectionCount();
    int secondMaxMonkeyIndex = findMaxInspectionsSmallerThan(maxInspections);
    int secondMaxInspections = monkeys.get(secondMaxMonkeyIndex).getInspectionCount();
    return maxInspections * secondMaxInspections;
  }

  private int findMaxInspectionsSmallerThan(int threshold) {
    int maxIndex = -1;
    int maxValue = -1;
    for (int i = 0; i < monkeys.size(); ++i) {
      int inspectionCount = monkeys.get(i).getInspectionCount();
      if (inspectionCount > maxValue && inspectionCount < threshold) {
        maxValue = inspectionCount;
        maxIndex = i;
      }
    }
    return maxIndex;
  }
}
