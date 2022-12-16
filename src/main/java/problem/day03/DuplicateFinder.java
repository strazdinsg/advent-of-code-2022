package problem.day03;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * A rucksack with two compartments. Can identify the type of item placed in both compartments.
 */
public class DuplicateFinder {
  // Don't allow instantiating the class
  private DuplicateFinder() {
  }

  /**
   * Find duplicate character found in both compartments of the rucksack.
   *
   * @param rucksack String representing the whole rucksack
   * @return The duplicate character
   */
  public static Character findDuplicateInBothCompartments(String rucksack) {
    return findDuplicateChar(splitInTwo(rucksack));
  }

  /**
   * Find duplicate character - the one found in all provided strings.
   *
   * @param items Strings where to search for duplicates
   * @return The character which is present in all the string items
   */
  public static Character findDuplicateChar(String[] items) {
    List<Set<Character>> characterSets = new LinkedList<>();
    for (String item : items) {
      characterSets.add(getUniqueCharacters(item));
    }
    Set<Character> duplicateCharacters = characterSets.get(0);
    for (Set<Character> set : characterSets) {
      keepOnlyDuplicates(duplicateCharacters, set);
    }
    if (duplicateCharacters.size() != 1) {
      throw new IllegalStateException("Expected exactly one duplicate character, found "
          + duplicateCharacters.size());
    }
    return duplicateCharacters.iterator().next();
  }

  /**
   * Create a rucksack string, split it into two equal compartments.
   *
   * @param rucksackString A string representing items in both compartments.
   * @return Two strings representing the two compartments
   */
  public static String[] splitInTwo(String rucksackString) {
    int itemCountInCompartment = rucksackString.length() / 2;
    String leftCompartment = rucksackString.substring(0, itemCountInCompartment);
    String rightCompartment = rucksackString.substring(itemCountInCompartment);
    return new String[]{leftCompartment, rightCompartment};
  }

  private static void keepOnlyDuplicates(Set<Character> left, Set<Character> right) {
    left.retainAll(right);
  }

  private static Set<Character> getUniqueCharacters(String compartment) {
    Set<Character> uniqueTypes = new HashSet<>();
    for (int i = 0; i < compartment.length(); ++i) {
      uniqueTypes.add(compartment.charAt(i));
    }
    return uniqueTypes;
  }

  /**
   * Get the priority of the character (item type), according to the logic described in the task.
   *
   * @param c Character to check
   * @return The priority of the duplicate item type
   */
  public static int getPriority(char c) {
    if (Character.isUpperCase(c)) {
      return c - 'A' + 27;
    } else {
      return c - 'a' + 1;
    }
  }
}
