import org.junit.jupiter.api.Test;
import problem.day03.DuplicateFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuckSackTest {
  @Test
  void testSimpleCases() {
    assertEquals(1, DuplicateFinder.getPriority('a'));
    assertEquals(2, DuplicateFinder.getPriority('b'));
    assertEquals(27, DuplicateFinder.getPriority('A'));
    assertEquals(52, DuplicateFinder.getPriority('Z'));

    assertEquals('B', DuplicateFinder.findDuplicateChar(new String[]{"aBcdef", "ABCDEF"}));
  }

  @Test
  void testGivenCases() {
    String[] compartments = DuplicateFinder.splitInTwo("vJrwpWtwJgWrhcsFMMfFFhFp");
    assertEquals(2, compartments.length);
    assertEquals("vJrwpWtwJgWr", compartments[0]);
    assertEquals("hcsFMMfFFhFp", compartments[1]);

    assertEquals('p', DuplicateFinder.findDuplicateChar(compartments));
    assertEquals(16, DuplicateFinder.getPriority('p'));

    assertEquals('L', DuplicateFinder.findDuplicateInBothCompartments(
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"));

    assertEquals('P', DuplicateFinder.findDuplicateInBothCompartments(
        "PmmdzqPrVvPwwTWBwg"));
  }

  @Test
  void testBadgeSearch() {
    testGroupBadge(
        "vJrwpWtwJgWrhcsFMMfFFhFp",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
        "PmmdzqPrVvPwwTWBwg",
        18
    );
    testGroupBadge(
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
        "ttgJtRGJQctTZtZT",
        "CrZsJsPPZsGzwwsLwLmpwMDw",
        52
    );
  }

  private void testGroupBadge(String rucksack1, String rucksack2, String rucksack3,
                              int expectedBadgePriority) {
    String[] elfSacks = new String[]{rucksack1, rucksack2, rucksack3};
    Character badge = DuplicateFinder.findDuplicateChar(elfSacks);
    assertEquals(expectedBadgePriority, DuplicateFinder.getPriority(badge));
  }
}
