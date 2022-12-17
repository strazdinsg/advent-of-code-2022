package problem.day07;

/**
 * File information.
 */
public class FileInfo {
  private final String name;
  private final int size;

  /**
   * Create a FileInfo object from a string.
   *
   * @param info The string containing file information
   * @throws IllegalArgumentException If the file info string is having incorrect format
   */
  public FileInfo(String info) throws IllegalArgumentException {
    String[] parts = info.split(" ");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid file info string: " + info);
    }

    name = parts[1];
    try {
      size = Integer.parseInt(parts[0]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid file size: " + info);
    }
  }

  public String getName() {
    return name;
  }

  public int getSize() {
    return size;
  }
}
