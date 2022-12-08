package problem.day01;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores up to n top values (the highest values).
 * This is NOT a MaxHeap: the operation complexity differs and the operations differ.
 * It works for the simple purpose though.
 */
public class TopValues {
  private final List<Long> values = new LinkedList<>();
  private final int size;

  /**
   * Create a list for storing highest values.
   *
   * @param size The number of items to keep in this list
   */
  public TopValues(int size) {
    this.size = size;
  }

  /**
   * Add the number to the list, remove the lowest value (if too many values are stored).
   *
   * @param value The value to add.
   */
  public void addIfHighest(Long value) {
    values.add(value);
    if (values.size() > size) {
      removeLowestValue();
    }
  }

  /**
   * Remove the lowest value from the list.
   */
  private void removeLowestValue() {
    long lowestValue = values.get(0);
    int lowestValueIndex = 0;
    int i = 0;
    for (Long v : values) {
      if (v < lowestValue) {
        lowestValue = v;
        lowestValueIndex = i;
      }
      ++i;
    }
    values.remove(lowestValueIndex);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Long v : values) {
      s.append(v).append(" ");
    }
    return s.toString();
  }

  /**
   * Get the sum of the values in the heap.
   *
   * @return The sum of values
   */
  public Long sum() {
    Long sum = 0L;
    for (Long v : values) {
      sum += v;
    }
    return sum;
  }
}
