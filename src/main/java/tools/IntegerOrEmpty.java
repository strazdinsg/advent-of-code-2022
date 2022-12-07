package tools;

/**
 * An integer which can have an empty value.
 */
public class IntegerOrEmpty {
  private final Long value;

  /**
   * An empty value.
   */
  public static final IntegerOrEmpty empty = new IntegerOrEmpty(null);


  private IntegerOrEmpty(Long value) {
    this.value = value;
  }

  /**
   * Create an object from a given integer value.
   *
   * @param value The integer value
   * @return An object representing the given integer value
   * @throws NullPointerException When the provided value is null
   */
  public static IntegerOrEmpty fromValue(Long value) throws NullPointerException {
    if (value == null) {
      throw new NullPointerException("Value must be non-null");
    }

    return new IntegerOrEmpty(value);
  }

  /**
   * Check if the value is a number.
   *
   * @return True if a number value is stored here, false when it is empty.
   */
  public boolean isNumber() {
    return value != null;
  }

  /**
   * Get the value.
   *
   * @return The integer value.
   * @throws NullPointerException if there is no value to return
   */
  public Long getValue() throws NullPointerException {
    if (value == null) {
      throw new NumberFormatException("No value to return!");
    }

    return value;
  }
}
