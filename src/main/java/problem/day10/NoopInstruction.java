package problem.day10;

/**
 * A No-operation instruction.
 */
public class NoopInstruction extends CpuInstruction {
  @Override
  public int getNecessaryCycles() {
    return 1;
  }

  @Override
  public void doExecute(CentralProcessorUnit cpu) {
    // No operation, by definition
  }
}
