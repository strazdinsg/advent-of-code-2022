package tools;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A collection of non-overlapping integer ranges.
 */
public class NonOverlappingRanges {
  private List<IntegerRange> ranges = new LinkedList<>();

  /**
   * Add a new range. Merge ranges if necessary.
   *
   * @param range The range to add.
   */
  public void add(IntegerRange range) {
    if (ranges.isEmpty()) {
      ranges.add(range);
    } else {
      removeTotallyCovered(range);
      mergeWithExisting(range);
    }
  }

  private void removeTotallyCovered(IntegerRange range) {
    for (int i = 0; i < ranges.size(); ++i) {
      IntegerRange existingRange = ranges.get(i);
      if (range.containsFully(existingRange)) {
        ranges.remove(i);
        i--;
      }
    }
  }

  /**
   * Merge the new range r with existing ranges. Assume that no range will overlap with r fully.
   * Find ranges s and e first:
   * s: where the new range starts
   * e: where the new range ends
   * Three cases possible:
   * a) If both s and e are found, replace them with a new range from s.start until e.end
   * b) If one of s or e exists, merge r with it
   * c) If neither s nor e are found, simply add the new range r
   *
   * @param r The new range to merge with existing ones.
   */
  private void mergeWithExisting(IntegerRange r) {
    IntegerRange s = findStartRangeFor(r);
    IntegerRange e = findEndRangeFor(r);
    if (s != null && e != null) {
      // If s == e, this means that r is fully within s and "gets swallowed by s"
      if (s != e) {
        s.setEnd(e.getEnd());
        ranges.remove(e);
      }
    } else if (s != null) {
      s.setEnd(r.getEnd());
    } else if (e != null) {
      e.setStart(r.getStart());
    } else {
      ranges.add(r);
    }
  }

  /**
   * Find an existing range which covers r.start .
   *
   * @param r The range to check the start for
   * @return A range which covers the start of r, or null if none found
   */
  private IntegerRange findStartRangeFor(IntegerRange r) {
    IntegerRange startRange = null;
    Iterator<IntegerRange> it = ranges.iterator();
    while (it.hasNext() && startRange == null) {
      IntegerRange range = it.next();
      if (r.startsWithin(range)) {
        startRange = range;
      }
    }
    return startRange;
  }

  /**
   * Find an existing range which covers r.end .
   *
   * @param r The range to check the end for
   * @return A range which covers the end of r, or null if none found
   */
  private IntegerRange findEndRangeFor(IntegerRange r) {
    IntegerRange endRange = null;
    Iterator<IntegerRange> it = ranges.iterator();
    while (it.hasNext() && endRange == null) {
      IntegerRange range = it.next();
      if (r.endsWithin(range)) {
        endRange = range;
      }
    }
    return endRange;
  }

  /**
   * Remove the single value from the given ranges, if it overlaps with any.
   *
   * @param v The value to remove
   */
  public void removeSingleValue(int v) {
    boolean found = false;
    Iterator<IntegerRange> it = ranges.iterator();
    while (it.hasNext() && !found) {
      IntegerRange r = it.next();
      if (r.containsValue(v)) {
        found = true;
        if (r.getStart() == v) {
          r.setStart(v + 1);
        } else if (r.getEnd() == v) {
          r.setEnd(v - 1);
        } else if (r.containsValue(v)) {
          int end = r.getEnd();
          r.setEnd(v - 1);
          ranges.add(new IntegerRange(v + 1, end));
        }
      }
    }
  }

  /**
   * Get the total sum of all range lengths.
   *
   * @return The sum of range lengths, 0 if no ranges found
   */
  public int getLengthSum() {
    int sum = 0;
    for (IntegerRange range : ranges) {
      sum += range.getLength();
    }
    return sum;
  }

  /**
   * Get the number of non-overlapping ranges.
   *
   * @return The number of non-overlapping ranges stored inside this collection
   */
  public int getRangeCount() {
    return ranges.size();
  }

  /**
   * Get the range stored at specific index.
   *
   * @param index The index of the range, indexing starts at zero.
   * @return The integer range
   * @throws ArrayIndexOutOfBoundsException When index is invalid
   */
  public IntegerRange getRange(int index) {
    return ranges.get(index);
  }
}
