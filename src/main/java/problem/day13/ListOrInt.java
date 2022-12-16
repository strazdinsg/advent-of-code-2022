package problem.day13;

import java.util.LinkedList;
import java.util.List;

/**
 * A list that can contain either integers or other lists. Effectively: all elements of
 * this list are ListOrInt objects.
 */
public class ListOrInt implements Comparable<ListOrInt> {
  // If this is set, this object represents a single integer
  private Integer integerValue;
  // If this is set, this object contains a list of integers or sub-lists
  private List<ListOrInt> listItems;

  /**
   * Create a ListOrInt object from its string representation (as stored in the input file).
   *
   * @param s The string representation
   * @throws IllegalStateException If the provided string does not contain a valid list
   */
  public ListOrInt(String s) throws IllegalArgumentException {
    if (s == null || s.isEmpty()) {
      throw new IllegalArgumentException("Invalid list string: " + s);
    }

    if (startsWithBracket(s)) {
      parseAsList(removeBrackets(s));
    } else {
      parseAsSingleInteger(s);
    }
  }

  private ListOrInt() {
  }

  private boolean startsWithBracket(String s) {
    return s.length() > 0 && s.charAt(0) == '[';
  }

  private String removeBrackets(String s) throws IllegalArgumentException {
    if (s.length() < 2 || s.charAt(0) != '[' || s.charAt(s.length() - 1) != ']') {
      throw new IllegalArgumentException("Can't remove brackets from " + s);
    }
    return s.substring(1, s.length() - 1);
  }

  private void parseAsList(String s) {
    listItems = new LinkedList<>();

    while (!s.isEmpty()) {
      String itemSlice = findOneItemSlice(s);
      ListOrInt item = new ListOrInt(itemSlice);
      listItems.add(item);
      s = removeParsedSlice(s, itemSlice);
      s = removeStartingComma(s);
    }
  }

  private String findOneItemSlice(String s) {
    int lastSliceCharPosition;
    if (startsWithBracket(s)) {
      lastSliceCharPosition = findMatchingClosingBracket(s);
    } else {
      lastSliceCharPosition = findEndOfFirstInteger(s);
    }
    return s.substring(0, lastSliceCharPosition + 1);
  }

  /**
   * Find the position of a matching closing bracket ']'.
   * Assume that the string starts with an opening bracket '['.
   *
   * @param s String which starts with a bracket
   * @return position of the matching closing backet ']'
   * @throws IllegalArgumentException if the matching bracket is not found
   */
  private int findMatchingClosingBracket(String s) throws IllegalArgumentException {
    if (!startsWithBracket(s)) {
      throw new IllegalArgumentException("First char is not an opening bracket: " + s);
    }
    int openedBrackets = 1;
    int position = 0;

    while (openedBrackets > 0) {
      position++;

      if (position >= s.length()) {
        throw new IllegalArgumentException("Closing bracket not found: " + s);
      }
      if (s.charAt(position) == '[') {
        openedBrackets++;
      } else if (s.charAt(position) == ']') {
        openedBrackets--;
      }
    }

    return position;
  }

  private int findEndOfFirstInteger(String s) {
    int commaPosition = s.indexOf(',');
    return commaPosition > 0 ? commaPosition - 1 : s.length() - 1;
  }

  private String removeParsedSlice(String s, String slice) {
    return s.substring(slice.length());
  }

  private String removeStartingComma(String s) {
    return (!s.isEmpty() && s.charAt(0) == ',') ? s.substring(1) : s;
  }


  private void parseAsSingleInteger(String s) throws IllegalStateException {
    try {
      integerValue = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Expected a single integer, got " + s);
    }
  }

  /**
   * Compare this object to e.
   *
   * @param e Object to compare to
   * @return -1 if this is smaller than e, 0 if equal to e, +1 if this is greater than e.
   */
  @Override
  public int compareTo(ListOrInt e) {
    if (this.isInteger() && e.isInteger()) {
      return compareAsIntegers(e);
    } else {
      return compareAsLists(e);
    }
  }

  private int compareAsIntegers(ListOrInt e) {
    return this.integerValue.compareTo(e.integerValue);
  }

  private int compareAsLists(ListOrInt e) {
    ListOrInt left = this.convertToList();
    ListOrInt right = e.convertToList();

    int index = 0;
    int comparison = 0;
    while (index < left.listItems.size() && comparison == 0) {
      if (index >= right.listItems.size()) {
        comparison = 1;
      } else {
        comparison = left.listItems.get(index).compareTo(right.listItems.get(index));
      }
      ++index;
    }

    if (comparison == 0 && right.listItems.size() > index) {
      comparison = -1;
    }

    return comparison;
  }

  private ListOrInt convertToList() {
    return this.isInteger() ? createListFromInt(this.integerValue) : this;
  }

  private static ListOrInt createListFromInt(Integer integerValue) {
    ListOrInt li = new ListOrInt();
    li.listItems = new LinkedList<>();
    ListOrInt intItem = new ListOrInt();
    intItem.integerValue = integerValue;
    li.listItems.add(intItem);
    return li;
  }

  private boolean isInteger() {
    return integerValue != null;
  }

  @Override
  public String toString() {
    if (integerValue != null) {
      return "" + integerValue;
    } else {
      List<String> itemStrings = new LinkedList<>();
      for (ListOrInt li : listItems) {
        itemStrings.add(li.toString());
      }
      return "[" + String.join(", ", itemStrings) + "]";
    }
  }
}
