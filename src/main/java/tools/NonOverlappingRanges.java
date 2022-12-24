package tools;

import java.util.Collection;
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
      mergeWithExistingRanges(range);
      mergeOverlapping();
    }
  }

  private void mergeWithExistingRanges(IntegerRange range) {
    List<IntegerRange> newRanges = new LinkedList<>();
    for (IntegerRange oldRange : ranges) {
      // TODO - fix this
      newRanges.addAll(merge(oldRange, range));
    }
    ranges = newRanges;
  }

  /**
   * Merge the two ranges, remove overlapping regions. If one range fully contains the other,
   * return the longest range. If they overlap, merge them in a single range. Otherwise,
   * return both.
   *
   * @param r1 First range
   * @param r2 Second range
   * @return The merging result
   */
  private Collection<IntegerRange> merge(IntegerRange r1, IntegerRange r2) {
    List<IntegerRange> mergedRanges = new LinkedList<>();

    if (r1.containsFully(r2)) {
      mergedRanges.add(r1);
    } else if (r2.containsFully(r1)) {
      mergedRanges.add(r2);
    } else if (r2.startsWithin(r1)) {
      mergedRanges.add(r1.extendEnd(r2.getEnd()));
    } else if (r1.startsWithin(r2)) {
      mergedRanges.add(r2.extendEnd(r1.getEnd()));
    } else {
      mergedRanges.add(r1);
      mergedRanges.add(r2);
    }

    return mergedRanges;
  }

  /**
   * As a result of adding a new range some resulting ranges may overlap. Go through all
   * pairs of ranges. If any pair overlaps, merge them.
   */
  private void mergeOverlapping() {
    for (int i = 1; i < ranges.size(); ++i) {
      boolean replaced = checkOverlapAndReplace(i);
      if (replaced) {
        // i-th range has been replaced, check the "new i-th" range again
        i--;
      }
    }
  }

  private boolean checkOverlapAndReplace(int rangeIndex) {
    IntegerRange r = ranges.get(rangeIndex);
    boolean replaced = false;
    int j = 0;
    while (!replaced && j < rangeIndex) {
      IntegerRange rj = ranges.get(j);
      if (r.overlapsWith(rj)) {
        ranges.set(j, r.mergeWith(rj));
        ranges.remove(rangeIndex);
        replaced = true;
      }
      ++j;
    }

    return replaced;
  }

  /**
   * Remove the single value from the given ranges, if it overlaps with any.
   *
   * @param v The value to remove
   */
  public void removeSingleValue(int v) {
    // TODO - refactor to a while-loop
    for (int i = 0; i < ranges.size(); ++i) {
      IntegerRange r = ranges.get(i);
      if (r.getStart() == v) {
        ranges.set(i, new IntegerRange(v + 1, r.getEnd()));
      } else if (r.getEnd() == v) {
        ranges.set(i, new IntegerRange(r.getStart(), v - 1));
      } else if (r.containsValue(v)) {
        IntegerRange[] splitRanges = r.cutAt(v);
        ranges.set(i, splitRanges[0]);
        ranges.add(splitRanges[1]);
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
