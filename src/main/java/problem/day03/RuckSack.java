package problem.day03;

import java.util.HashSet;
import java.util.Set;

/**
 * A rucksack with two compartments. Can identify the type of item placed in both compartments.
 */
public class RuckSack {
  private final Character duplicateType;

  /**
   * Create a rucksack.
   *
   * @param leftCompartment  A string representing the items in the left compartment
   * @param rightCompartment A string representing the items in the right compartment
   */
  public RuckSack(String leftCompartment, String rightCompartment) {
    Set<Character> itemTypesOnLeft = getUniqueTypes(leftCompartment);
    Set<Character> itemTypesOnRight = getUniqueTypes(rightCompartment);
    duplicateType = findDuplicate(itemTypesOnLeft, itemTypesOnRight);
  }

  /**
   * Create a rucksack from a string representing both compartments.
   *
   * @param itemString A string representing items in both compartments.
   * @return A rucksack where the duplicate items have been identified
   */
  public static RuckSack createFrom(String itemString) {
    int itemCountInCompartment = itemString.length() / 2;
    String leftCompartment = itemString.substring(0, itemCountInCompartment);
    String rightCompartment = itemString.substring(itemCountInCompartment);
    return new RuckSack(leftCompartment, rightCompartment);
  }


  private Character findDuplicate(Set<Character> left, Set<Character> right) {
    keepOnlyDuplicatesInLeft(left, right);
    if (left.isEmpty()) {
      throw new IllegalStateException("No duplicates found!");
    }
    return left.iterator().next();
  }

  private void keepOnlyDuplicatesInLeft(Set<Character> left, Set<Character> right) {
    left.retainAll(right);
  }

  private Set<Character> getUniqueTypes(String compartment) {
    Set<Character> uniqueTypes = new HashSet<>();
    for (int i = 0; i < compartment.length(); ++i) {
      uniqueTypes.add(compartment.charAt(i));
    }
    return uniqueTypes;
  }

  /**
   * Get the priority of the duplicate item type, according to the logic described in the task.
   *
   * @return The priority of the duplicate item type
   */
  public int getDuplicateItemPriority() {
    if (duplicateType == null) {
      throw new IllegalStateException("No duplicate type found!");
    }
    if (Character.isUpperCase(duplicateType)) {
      return duplicateType - 'A' + 27;
    } else {
      return duplicateType - 'a' + 1;
    }
  }
}
