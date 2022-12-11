package problem.day10;

/**
 * Represents a CPU instruction.
 */
abstract class CpuInstruction {

  protected CpuInstruction() {
  }

  /**
   * Decodes a CPU instruction from a String.
   *
   * @param instructionString A string command received from the input file
   * @return The decoded CPU instruction
   * @throws IllegalArgumentException When something is wrong with the input string
   */
  public static CpuInstruction createFrom(String instructionString)
      throws IllegalArgumentException {
    if (instructionString == null || instructionString.length() < 4) {
      throw new IllegalArgumentException("Invalid instruction: " + instructionString);
    }
    String typeString = instructionString.substring(0, 4);
    return switch (typeString) {
      case "noop" -> new NoopInstruction();
      case "addx" -> new AddInstruction(parseArgument(instructionString));
      default -> throw new IllegalArgumentException("Invalid instruction type: " + typeString);
    };
  }

  private static int parseArgument(String argumentString) throws IllegalArgumentException {
    try {
      return Integer.parseInt(argumentString.substring(5));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid argument: " + argumentString);
    }
  }

  /**
   * Get the number of cycles necessary to execute this instruction.
   *
   * @return The number of CPU cycles needed to execute this instruction
   */
  public abstract int getNecessaryCycles();

  /**
   * Execute the instruction on the given CPU - do it's job.
   *
   * @param cpu The CPU to run this instruction on.
   */
  public abstract void doExecute(CentralProcessorUnit cpu);
}
