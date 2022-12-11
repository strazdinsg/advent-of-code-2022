package problem.day10;

/**
 * The ADD instruction.
 */
public class AddInstruction extends CpuInstruction {
  private final int argument;

  public AddInstruction(int argument) {
    this.argument = argument;
  }

  @Override
  public int getNecessaryCycles() {
    return 2;
  }

  @Override
  public void doExecute(CentralProcessorUnit cpu) {
    cpu.increaseRegister(argument);
  }
}
