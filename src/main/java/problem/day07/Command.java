package problem.day07;

/**
 * A command that can be executed in a file system.
 */
public class Command {
  private final CommandType type;
  private final String argument;

  /**
   * Create a command.
   *
   * @param commandString String representation of the command
   * @throws IllegalArgumentException If the command string has an invalid format
   */
  public Command(String commandString) throws IllegalArgumentException {
    if (commandString == null) {
      throw new IllegalArgumentException("Command string can't be null");
    }

    String typeString = commandString.substring(2, 4);
    type = switch (typeString) {
      case "cd" -> CommandType.CD;
      case "ls" -> CommandType.LS;
      default -> throw new IllegalArgumentException("Invalid command type: " + typeString);
    };

    if (type == CommandType.CD) {
      String[] parts = commandString.split(" ");
      if (parts.length != 3) {
        throw new IllegalArgumentException("cd command is missing an argument");
      }
      argument = parts[2];
    } else {
      argument = null;
    }
  }

  public CommandType getType() {
    return type;
  }

  public String getArgument() {
    return argument;
  }
}
