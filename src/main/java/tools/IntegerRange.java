package tools;

import java.util.Objects;

/**
 * A range of integers.
 */
public class IntegerRange {
  private final int start;
  private final int end;

  /**
   * Create an integer range, which spans from the start to the end, inclusive.
   *
   * @param start The start of the range
   * @param end   The end of the range
   * @throws IllegalArgumentException If start > end
   */
  public IntegerRange(int start, int end) throws IllegalArgumentException {
    if (start > end) {
      throw new IllegalArgumentException("Start (" + start + ") can't be higher than end("
          + end + ")");
    }
    this.start = start;
    this.end = end;
  }

  /**
   * Check whether this range includes range r fully.
   *
   * @param r The range to check for
   * @return True if this range includes range r fully, false otherwise
   */
  public boolean containsFully(IntegerRange r) {
    return r != null && start <= r.start && end >= r.end;
  }

  /**
   * Creates a new integer range which represents this minus r. This can't contain r fully
   * and vice versa.
   *
   * @param r The range to subtract
   * @return A new integer range representing this - m
   * @throws IllegalArgumentException When preconditions for r are not met.
   */
  public IntegerRange minus(IntegerRange r) throws IllegalArgumentException {
    if (r == null) {
      throw new IllegalArgumentException("Can't subtract a null range");
    } else if (this.containsFully(r) || r.containsFully(this)) {
      throw new IllegalArgumentException("minus operation can't be used on fully-contained ranges."
          + " Called for " + this + " and " + r);
    }

    IntegerRange result;
    if (r.startsWithin(this)) {
      result = new IntegerRange(this.start, r.start - 1);
    } else if (r.endsWithin(this)) {
      result = new IntegerRange(r.end + 1, this.end);
    } else {
      result = new IntegerRange(this.start, this.end);
    }

    return result;
  }

  /**
   * Check whether this starts within the range r.
   *
   * @param r The range to check
   * @return True if this starts inside range r
   */
  public boolean startsWithin(IntegerRange r) {
    return this.start >= r.start && this.start <= r.end;
  }

  /**
   * Check whether this end within the range r.
   *
   * @param r The range to check
   * @return True if this end inside range r
   */
  public boolean endsWithin(IntegerRange r) {
    return this.end >= r.start && this.end <= r.end;
  }

  @Override
  public String toString() {
    return "[" + start + ".." + end + "]";
  }

  /**
   * Get the end of the range.
   *
   * @return The integer representing the end of the range
   */
  public int getEnd() {
    return end;
  }

  /**
   * Get the start of the interval.
   *
   * @return The integer value representing the start of the interval
   */
  public int getStart() {
    return start;
  }

  /**
   * Extend the range.
   *
   * @param newEnd The new end-boundary of the range.
   * @return A new range where the start is the same, but the end is set to newEnd
   * @throws IllegalArgumentException When the new end is smaller than the previous end
   */
  public IntegerRange extendEnd(int newEnd) throws IllegalArgumentException {
    if (newEnd < end) {
      throw new IllegalArgumentException("Invalid extension from " + end + " to " + newEnd);
    }
    return new IntegerRange(start, newEnd);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IntegerRange that = (IntegerRange) o;
    return start == that.start && end == that.end;
  }

  @Override
  public int hashCode() {
    return Objects.hash(start, end);
  }

  public boolean overlapsWith(IntegerRange r) {
    return this.startsWithin(r) || this.endsWithin(r) || this.containsFully(r);
  }

  /**
   * Merge this range with another range. Note: this range is unchanged!
   *
   * @param r The new range to merge with
   * @return A new range where this and r are merged
   * @throws IllegalArgumentException When this and r don't overlap
   */
  public IntegerRange mergeWith(IntegerRange r) throws IllegalArgumentException {
    if (!this.overlapsWith(r)) {
      throw new IllegalArgumentException("Can't merge two non-overlapping ranges "
          + this + " and " + r);
    }

    return new IntegerRange(Math.min(this.start, r.start), Math.max(this.end, r.end));
  }

  /**
   * Check if this range contains value v.
   *
   * @param v The value to check
   * @return True if v fits within this range
   */
  public boolean containsValue(int v) {
    return start <= v && end >= v;
  }

  /**
   * Cut this range into two - around the given cutValue. Note: this range is left unchanged!
   *
   * @param cutValue The value to cut at. This will be not included in the new ranges.
   * @return Two new ranges: one starting at start and ending at cutValue - 1, the other starting
   *     at cutValue + 1 and ending at end.
   * @throws IllegalArgumentException if the cutValue is not inside the range or if it overlaps
   *                                  with start or end
   */
  public IntegerRange[] cutAt(int cutValue) throws IllegalArgumentException {
    if (cutValue <= start || cutValue >= end) {
      throw new IllegalArgumentException("Invalid cut value (" + cutValue + ") for range " + this);
    }

    return new IntegerRange[]{
        new IntegerRange(start, cutValue - 1),
        new IntegerRange(cutValue + 1, end)
    };
  }

  /**
   * Get the length of the range, inclusive start and end.
   *
   * @return The length of the range.
   */
  public int getLength() {
    return end - start + 1;
  }
}
