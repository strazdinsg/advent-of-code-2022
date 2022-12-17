package problem.day06;

import java.util.HashSet;
import java.util.Set;

/**
 * Consumes characters, detects start signal position (first occurrence where four characters
 * are all different).
 */
public class StartSignalDetector {
  final char[] characters;
  int processedCharCount;
  int signalLength;

  final Set<Character> uniqueChars = new HashSet<>();

  /**
   * Create a new Start-signal detector.
   *
   * @param signalLength The expected length of the start signal
   */
  public StartSignalDetector(int signalLength) {
    this.signalLength = signalLength;
    characters = new char[signalLength];
    processedCharCount = 0;
  }

  /**
   * Add the next character to the detector.
   *
   * @param c The character to add
   */
  public void add(char c) {
    int replacementIndex = processedCharCount % signalLength;
    characters[replacementIndex] = c;
    processedCharCount++;
  }

  /**
   * Get the number of characters processed so far.
   *
   * @return Processed character count
   */
  public Integer getProcessedCharacterCount() {
    return processedCharCount;
  }

  /**
   * Check if the currently buffered characters form a correct start sequence.
   *
   * @return True when start sequence is now detected, false otherwise.
   */
  public boolean isDetected() {
    return processedCharCount >= signalLength
        && getNumberOfUniqueChars() == signalLength;
  }

  private int getNumberOfUniqueChars() {
    uniqueChars.clear();
    for (char c : characters) {
      uniqueChars.add(c);
    }
    return uniqueChars.size();
  }
}
