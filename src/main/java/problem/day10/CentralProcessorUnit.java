package problem.day10;

/**
 * Emulates a CPU.
 */
public class CentralProcessorUnit {
  private boolean isDone = false;
  private int ticksLeftForCurrentInstruction = 0;
  private int cycleNumber = 1;
  private CpuInstruction instruction;
  private int registerValue = 1;

  public boolean isNotDone() {
    return !isDone;
  }

  /**
   * Whether the CPU is idle.
   *
   * @return True when CPU is idle and ready to start executing the next instruction
   */
  public boolean isIdle() {
    return instruction == null;
  }

  /**
   * Start executing the next instruction.
   *
   * @param instruction The CPU instruction to execute
   * @throws IllegalStateException When there is an unfinished instruction still on the CPU
   */
  public void scheduleInstruction(CpuInstruction instruction) throws IllegalStateException {
    if (!isIdle()) {
      throw new IllegalStateException("CPU still busy, can't schedule another instruction!");
    }
    if (instruction != null) {
      this.instruction = instruction;
      ticksLeftForCurrentInstruction = instruction.getNecessaryCycles();
    } else {
      isDone = true; // When we receive null instruction from the input file, the party is over
    }
  }

  /**
   * Get the number of current cycle (the one already finished).
   *
   * @return The number of the last finished CPU cycle
   */
  public int getCycleNumber() {
    return cycleNumber;
  }

  /**
   * Get the current value of the CPU register.
   *
   * @return THe register value
   */
  public int getRegisterValue() {
    return registerValue;
  }

  /**
   * Do execution, increase the cycle number.
   *
   * @throws IllegalStateException If the tick happens in an unexpected state
   */
  public void tick() throws IllegalStateException {
    cycleNumber++;
    if (isDone) {
      return;
    }

    if (instruction == null) {
      throw new IllegalStateException("Can't tick without an instruction! Cycle " + cycleNumber);
    }

    ticksLeftForCurrentInstruction--;
    if (isExecutionComplete()) {
      instruction.doExecute(this);
      instruction = null;
    }
  }

  private boolean isExecutionComplete() {
    return ticksLeftForCurrentInstruction == 0;
  }

  /**
   * Increas the register value by x.
   *
   * @param x the value to add to the register
   */
  public void increaseRegister(int x) {
    registerValue += x;
  }
}
