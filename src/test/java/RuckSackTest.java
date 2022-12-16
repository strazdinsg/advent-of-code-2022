import org.junit.jupiter.api.Test;
import problem.day03.RuckSack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuckSackTest {
  @Test
  void testSimpleCases() {
    RuckSack r = new RuckSack("A", "A");
    assertEquals(27, r.getDuplicateItemPriority());

    r = new RuckSack("ABC", "CD");
    assertEquals(29, r.getDuplicateItemPriority());

    r = new RuckSack("aBcdef", "ABCDEF");
    assertEquals(28, r.getDuplicateItemPriority());
  }

  @Test
  void testGivenCases() {
    RuckSack r = new RuckSack("vJrwpWtwJgWr", "hcsFMMfFFhFp");
    assertEquals(16, r.getDuplicateItemPriority());

    r = new RuckSack("jqHRNqRjqzjGDLGL", "rsFMfFZSrLrFZsSL");
    assertEquals(38, r.getDuplicateItemPriority());

    r = RuckSack.createFrom("PmmdzqPrVvPwwTWBwg");
    assertEquals(42, r.getDuplicateItemPriority());

    r = RuckSack.createFrom("wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn");
    assertEquals(22, r.getDuplicateItemPriority());

    r = RuckSack.createFrom("ttgJtRGJQctTZtZT");
    assertEquals(20, r.getDuplicateItemPriority());

    r = RuckSack.createFrom("CrZsJsPPZsGzwwsLwLmpwMDw");
    assertEquals(19, r.getDuplicateItemPriority());
  }
}
