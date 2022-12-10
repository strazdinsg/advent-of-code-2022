package problem.day09;

import tools.Logger;
import tools.Vector;

/**
 * Represents a rope with a head and tail.
 */
public class Rope {
  Vector head;
  Vector tail;

  private final InfiniteRopeField field;

  /**
   * Initialize a rope.
   *
   * @param field The infinite field in which the rope will be moving
   */
  public Rope(InfiniteRopeField field) {
    this.field = field;
    head = new Vector(0, 0);
    tail = new Vector(0, 0);
  }

  /**
   * Apply the given movement to the rope: move the head and the tail will follow.
   *
   * @param movement The desired movement
   */
  public void move(Movement movement) {
    while (movement != null && movement.isNotFinished()) {
      moveHead(movement.takeOneStep());
      tailFollowsHead();
    }
  }

  private void tailFollowsHead() {
    Vector distance = head.minus(tail);
    if (isHeadWayIllegallyFar(distance)) {
      throw new IllegalStateException("Head too far from the head, this should never happen!");
    }

    if (isHeadTooFarRight(distance)) {
      moveTail(1, distance.getY());
    } else if (isHeadTooFarLeft(distance)) {
      moveTail(-1, distance.getY());
    } else if (isHeadTooFarDown(distance)) {
      moveTail(distance.getX(), 1);
    } else if (isHeadTooFarUp(distance)) {
      moveTail(distance.getX(), -1);
    }
  }

  private boolean isHeadWayIllegallyFar(Vector distance) {
    return distance.getAbsoluteX() > 2 || distance.getAbsoluteY() > 2;
  }

  private boolean isHeadTooFarRight(Vector headTailDistance) {
    return headTailDistance.getX() == 2;
  }

  private boolean isHeadTooFarLeft(Vector headTailDistance) {
    return headTailDistance.getX() == -2;
  }

  private boolean isHeadTooFarUp(Vector headTailDistance) {
    return headTailDistance.getY() == -2;
  }

  private boolean isHeadTooFarDown(Vector headTailDistance) {
    return headTailDistance.getY() == 2;
  }

  private void moveHead(Vector step) {
    head = head.plus(step);
    Logger.info("Head at " + head);
  }

  private void moveTail(int deltaX, int deltaY) {
    tail = tail.plus(new Vector(deltaX, deltaY));
    Logger.info("  Tail at " + tail);
    field.registerTailPosition(tail);
  }
}
