package problem.day09;

import java.util.HashSet;
import java.util.Set;
import tools.Logger;
import tools.Vector;

/**
 * Represents an infinite field where the position of the rope (the tail) is monitored.
 * Can track the number of unique positions the tail has visited.
 */
public class InfiniteRopeField {
  private int uniquePositionCount = 0;
  private final Set<Vector> previousTailPositions = new HashSet<>();

  /**
   * Get the number of unique positions visited.
   *
   * @return The number of unique positions the tail has visited
   */
  public int getUniqueVisitedPositionCount() {
    return uniquePositionCount;
  }

  /**
   * Register that the tail has moved to the provided position.
   *
   * @param position The new tail position
   */
  public void registerTailPosition(Vector position) {
    if (!hasTailVisited(position)) {
      addUniquePosition(position);
    }
  }

  private void addUniquePosition(Vector tailPosition) {
    Logger.info("    Tail at NEW position");
    previousTailPositions.add(tailPosition);
    uniquePositionCount++;
  }

  private boolean hasTailVisited(Vector tailPosition) {
    return previousTailPositions.contains(tailPosition);
  }
}
