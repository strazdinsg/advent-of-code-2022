import org.junit.jupiter.api.Test;
import tools.IntegerRange;
import tools.NonOverlappingRanges;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoOverlappingRangesTest {
  @Test
  void testMergingTwo() {
    NonOverlappingRanges ranges = new NonOverlappingRanges();

    ranges.add(new IntegerRange(-2, 14));
    assertEquals(1, ranges.getRangeCount());
    assertEquals(new IntegerRange(-2, 14), ranges.getRange(0));

    ranges.add(new IntegerRange(16, 24));
    assertEquals(2, ranges.getRangeCount());
    assertEquals(new IntegerRange(-2, 14), ranges.getRange(0));
    assertEquals(new IntegerRange(16, 24), ranges.getRange(1));

    ranges.add(new IntegerRange(10, 18));
    assertEquals(1, ranges.getRangeCount());
    assertEquals(new IntegerRange(-2, 24), ranges.getRange(0));
  }

  @Test
  void testMergingMultiple() {
    NonOverlappingRanges ranges = new NonOverlappingRanges();

    ranges.add(new IntegerRange(-2, 14));
    ranges.add(new IntegerRange(18, 24));
    ranges.add(new IntegerRange(34, 40));
    ranges.add(new IntegerRange(50, 80));
    assertEquals(4, ranges.getRangeCount());
    assertEquals(new IntegerRange(-2, 14), ranges.getRange(0));
    assertEquals(new IntegerRange(18, 24), ranges.getRange(1));
    assertEquals(new IntegerRange(34, 40), ranges.getRange(2));
    assertEquals(new IntegerRange(50, 80), ranges.getRange(3));

    ranges.add(new IntegerRange(10, 51));
    assertEquals(1, ranges.getRangeCount());
    assertEquals(new IntegerRange(-2, 80), ranges.getRange(0));
  }
}
