package problem.day11;

import java.util.LinkedList;
import java.util.List;
import problem.day11.operation.IncrementOperation;
import problem.day11.operation.MultiplyOperation;
import problem.day11.operation.Operation;
import problem.day11.operation.SquareOperation;
import tools.InputFile;


/**
 * Reads monkey data from an input file.
 */
public class MonkeyFileParser {
  /**
   * Parse information of one monkey from the input file.
   *
   * @param inputFile   The input file to read
   * @param monkeyIndex The index of the monkey, starting at 0
   * @return a monkey
   * @throws IllegalArgumentException When something is not in the expected format in the input file
   */
  public static Monkey parse(InputFile inputFile, int monkeyIndex) throws IllegalArgumentException {
    validateMonkeyIntroLine(inputFile, monkeyIndex);
    List<Long> initialItems = parseInitialItems(inputFile);
    Operation operation = parseOperation(inputFile);
    DivisionCondition testCondition = parseTest(inputFile);
    ThrowActions actions = parseActions(inputFile);
    inputFile.skipEmptyLine();
    return new Monkey(initialItems, operation, testCondition, actions);
  }

  private static void validateMonkeyIntroLine(InputFile inputFile, int monkeyIndex) {
    String expectedContent = "Monkey " + monkeyIndex + ":";
    String fileContent = inputFile.readLine();
    if (!expectedContent.equals(fileContent)) {
      throw new IllegalArgumentException("Invalid header line: " + fileContent);
    }
  }

  private static List<Long> parseInitialItems(InputFile inputFile)
      throws IllegalArgumentException {
    List<Long> items = new LinkedList<>();
    String line = inputFile.readLine();
    if (!line.startsWith("  Starting items: ")) {
      throw new IllegalArgumentException("Invalid starting item line: " + line);
    }
    addItems(items, line.substring(18));
    return items;
  }

  private static void addItems(List<Long> items, String itemString)
      throws IllegalArgumentException {
    String[] parts = itemString.split(", ");
    if (parts.length < 1) {
      throw new IllegalArgumentException("No items in the list: " + itemString);
    }
    for (String s : parts) {
      try {
        items.add(Long.parseLong(s));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid item integer: " + s);
      }
    }
  }

  private static Operation parseOperation(InputFile inputFile) throws IllegalArgumentException {
    String line = inputFile.readLine();
    if (!line.startsWith("  Operation: new = ")) {
      throw new IllegalArgumentException("Invalid operation: " + line);
    }

    Operation op;
    String opString = line.substring(19);
    String operand = opString.substring(6);
    if (opString.equals("old * old")) {
      op = new SquareOperation();
    } else if (opString.startsWith("old * ")) {
      int multiplier = Integer.parseInt(operand);
      op = new MultiplyOperation(multiplier);
    } else if (opString.startsWith("old + ")) {
      int increment = Integer.parseInt(operand);
      op = new IncrementOperation(increment);
    } else {
      throw new IllegalArgumentException("Invalid operation: " + opString);
    }

    return op;
  }

  private static DivisionCondition parseTest(InputFile inputFile) throws IllegalArgumentException {
    String line = inputFile.readLine();
    if (!line.startsWith("  Test: divisible by ")) {
      throw new IllegalArgumentException("Invalid test line: " + line);
    }
    try {
      int divider = Integer.parseInt(line.substring(21));
      return new DivisionCondition(divider);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid divider in the test condition: " + line);
    }
  }

  private static ThrowActions parseActions(InputFile inputFile) {
    int trueThrowIndex = parseAction(inputFile, true);
    int falseThrowIndex = parseAction(inputFile, false);
    return new ThrowActions(trueThrowIndex, falseThrowIndex);
  }

  private static int parseAction(InputFile inputFile, boolean expectedCondition)
      throws IllegalArgumentException {
    String expectedLine = "    If " + expectedCondition + ": throw to monkey ";
    String line = inputFile.readLine();
    if (!line.startsWith(expectedLine)) {
      throw new IllegalArgumentException("Invalid action line: " + line);
    }

    String indexString = line.substring(expectedLine.length());

    try {
      return Integer.parseInt(indexString);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid throw-to-index: " + indexString);
    }
  }
}
