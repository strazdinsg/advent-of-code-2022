package problem.day05;

import java.util.List;
import java.util.Stack;

/**
 * Stacks of crates.
 */
public class CrateStacks {
  private Stack<Character>[] stacks;

  /**
   * Create stacks of crates from input-file strings.
   *
   * @param stackLines The lines from the input file depicting the initial configuration of the
   *                   crates in the stacks.
   */
  public CrateStacks(List<String> stackLines) {
    int stackCount = getStackCountFromStrings(stackLines);
    createStacks(stackCount);
    for (int stackIndex = 0; stackIndex < stackCount; ++stackIndex) {
      parseCratesInStack(stackLines, stackIndex);
    }
  }

  private void parseCratesInStack(List<String> stackLines, int stackIndex) {
    int lineNumber = stackLines.size() - 2;
    Character crate = getCrate(stackLines.get(lineNumber), stackIndex);
    while (lineNumber > 0 && crate != null) {
      stacks[stackIndex].push(crate);
      lineNumber--;
      crate = getCrate(stackLines.get(lineNumber), stackIndex);
    }

    if (crate != null) {
      stacks[stackIndex].push(crate);
    }
  }

  private Character getCrate(String stackLine, int stackIndex) {
    int charIndex = stackIndex * 4 + 1;
    Character c = charIndex < stackLine.length() ? stackLine.charAt(charIndex) : null;
    if (c != null && c.equals(' ')) {
      c = null;
    }
    return c;
  }

  private int getStackCountFromStrings(List<String> stackLines) {
    String lastLine = stackLines.get(stackLines.size() - 1);
    String[] stackIndices = lastLine.split(" ");
    try {
      return Integer.parseInt(stackIndices[stackIndices.length - 1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid stack index line: " + lastLine);
    }
  }

  private void createStacks(int stackCount) {
    stacks = new Stack[stackCount];
    for (int i = 0; i < stackCount; ++i) {
      stacks[i] = new Stack<>();
    }
  }

  /**
   * Get a string representing the top crates of all the stacks.
   *
   * @return The top crates
   */
  public String getTopCrates() {
    StringBuilder top = new StringBuilder();
    for (int stackIndex = 0; stackIndex < stacks.length; ++stackIndex) {
      Character topChar = getTopCrate(stackIndex);
      if (topChar != null) {
        top.append(topChar);
      }
    }
    return top.toString();
  }

  private Character getTopCrate(int stackIndex) {
    return stacks[stackIndex].isEmpty() ? null : stacks[stackIndex].peek();
  }

  /**
   * Move crates between the stacks according to the given command.
   *
   * @param command The command describing the necessary crate movemen
   */
  public void move(Command command) {
    for (int i = 0; i < command.getCount(); ++i) {
      moveOne(command.getFromIndex() - 1, command.getToIndex() - 1);
    }
  }

  private void moveOne(int fromStack, int toStack) {
    Character crate = stacks[fromStack].pop();
    stacks[toStack].push(crate);
  }
}
