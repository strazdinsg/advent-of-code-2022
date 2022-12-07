package tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Handles input-data files.
 */
public class InputFile {
  private boolean exists;
  private BufferedReader reader;
  private boolean endOfFileReached;

  /**
   * Open an input file for reading.
   *
   * @param filename The name of the input file
   */
  public InputFile(String filename) {
    try {
      reader = new BufferedReader(new FileReader(filename));
      exists = true;
    } catch (FileNotFoundException e) {
      exists = false;
    }
  }

  /**
   * Returns the "existence of the file".
   *
   * @return true if the file exists, false if it either is not found or is not readable.
   */
  public boolean exists() {
    return exists;
  }

  /**
   * Read one line of text from the input file, try to interpret it as an integer.
   *
   * @return The number value; or empty value if the line was empty or end of file is reached
   */
  public IntegerOrEmpty readLineAsInteger() {
    String value = readLineAndDetectEnd();
    return value != null ? convertToInt(value) : IntegerOrEmpty.empty;
  }

  private static IntegerOrEmpty convertToInt(String s) {
    if (s == null || s.equals("")) {
      return IntegerOrEmpty.empty;
    }
    return IntegerOrEmpty.fromValue(Long.valueOf(s));
  }

  /**
   * Read one line of text from the file and detect whether end of file has been reached.
   */
  private String readLineAndDetectEnd() {
    String value = null;
    try {
      value = reader.readLine();
      if (value == null) {
        endOfFileReached = true;
      }
    } catch (IOException e) {
      endOfFileReached = true;
    }
    return value;
  }

  public boolean isEndOfFile() {
    return endOfFileReached;
  }
}
