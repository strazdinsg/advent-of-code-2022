package problem.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Holds a list of signals and can sort them.
 */
public class SignalList {
  private final List<ListOrInt> items = new ArrayList<>();

  /**
   * Add an item to the list. Warning - the ordering is not ensured automatically!
   *
   * @param item The item to add
   */
  public void add(ListOrInt item) {
    items.add(item);
  }


  /**
   * Get the index of the item in the list, or -1 if it is not found.
   * Warning: indexing starts at 1.
   *
   * @param item the item to look for
   * @return Index of the item (starting at 1), or -1 if item is not found
   */
  public int indexOf(ListOrInt item) {
    int index = items.indexOf(item);
    return index >= 0 ? index + 1 : -1;
  }

  public void sort() {
    Collections.sort(items);
  }
}
