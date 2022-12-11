package problem.day10;

import java.util.HashSet;
import java.util.Set;
import tools.InputFile;
import tools.Logger;

/**
 * Solution for the problem of Day 10
 * See description here: https://adventofcode.com/2022/day/10
 * Parse CPU commands, track the value of accumulator register at specific cycles.
 * Idea: run the CPU in cycles, load the next operation when CPU is idle. Fixate register values
 * at specific time moments
 */
public class Solver {
  Set<Integer> interestingCycleNumbers;

  /**
   * Run the solver - solve the puzzle.
   *
   * @param args Command line arguments, not used (enforced by Java).
   */
  public static void main(String[] args) {
    Logger.info("Solving puzzle for Day 10...");
    Solver solver = new Solver();
    solver.solve();
  }

  private void solve() {
    InputFile inputFile = new InputFile("problem10.input");
    if (!inputFile.exists()) {
      return;
    }

    initializeInterestingCycleNumbers();

    CentralProcessorUnit cpu = new CentralProcessorUnit();
    int signalStrengthSum = 0;
    while (cpu.isNotDone()) {
      if (cpu.isIdle()) {
        cpu.scheduleInstruction(fetchInstructionFromFile(inputFile));
      }
      cpu.tick();
      if (isInterestingCycleNumber(cpu.getCycleNumber())) {
        signalStrengthSum += cpu.getCycleNumber() * cpu.getRegisterValue();
      }
    }

    Logger.info("Sum of signal strength values: " + signalStrengthSum);
  }

  private boolean isInterestingCycleNumber(int cycleNumber) {
    return interestingCycleNumbers.contains(cycleNumber);
  }

  private void initializeInterestingCycleNumbers() {
    interestingCycleNumbers = new HashSet<>();
    interestingCycleNumbers.add(20);
    interestingCycleNumbers.add(60);
    interestingCycleNumbers.add(100);
    interestingCycleNumbers.add(140);
    interestingCycleNumbers.add(180);
    interestingCycleNumbers.add(220);
  }

  private CpuInstruction fetchInstructionFromFile(InputFile inputFile) {
    String instructionString = inputFile.readLine();
    if (instructionString == null) {
      return null;
    }
    return CpuInstruction.createFrom(instructionString);
  }
}
