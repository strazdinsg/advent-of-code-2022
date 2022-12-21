package problem.day09;

import tools.Logger;
import tools.Rectangle;
import tools.Vector;

/**
 * Represents a rope with a head and tail.
 */
public class Rope {
  Vector[] knots;

  int numberOfKnots;

  private final InfiniteRopeField field;

  /**
   * Initialize a rope.
   *
   * @param field         The infinite field in which the rope will be moving
   * @param numberOfKnots The number of knots to create in the rope
   */
  public Rope(InfiniteRopeField field, int numberOfKnots) {
    this.field = field;
    this.numberOfKnots = numberOfKnots;
    knots = new Vector[numberOfKnots];
    for (int i = 0; i < numberOfKnots; ++i) {
      knots[i] = new Vector(0, 0);
    }
  }

  /**
   * Apply the given movement to the rope: move the head and the tail will follow.
   *
   * @param movement The desired movement
   */
  public void move(Movement movement) {
    while (movement != null && movement.isNotFinished()) {
      moveHead(movement.takeOneStep());
      for (int i = 1; i < numberOfKnots; ++i) {
        knotFollows(i);
      }
    }
  }

  private void knotFollows(int knotNumber) {
    Vector distance = knots[knotNumber - 1].minus(knots[knotNumber]);
    if (areKnotsIllegallyFar(distance)) {
      throw new IllegalStateException("One knot too far from the next, this should never happen!");
    }

    if (isTwoStepDiagonal(distance)) {
      Vector halfDiagonal = distance.scaleToOneUnit();
      moveKnot(knotNumber, halfDiagonal.getX(), halfDiagonal.getY());
    } else if (isHeadTooFarRight(distance)) {
      moveKnot(knotNumber, 1, distance.getY());
    } else if (isHeadTooFarLeft(distance)) {
      moveKnot(knotNumber, -1, distance.getY());
    } else if (isHeadTooFarDown(distance)) {
      moveKnot(knotNumber, distance.getX(), 1);
    } else if (isHeadTooFarUp(distance)) {
      moveKnot(knotNumber, distance.getX(), -1);
    }
  }

  private boolean isTwoStepDiagonal(Vector distance) {
    return Math.abs(distance.getX()) == 2 && Math.abs(distance.getY()) == 2;
  }

  private boolean areKnotsIllegallyFar(Vector distance) {
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
    knots[0] = knots[0].plus(step);
    Logger.info("Head at " + knots[0]);
  }

  private void moveKnot(int knotNumber, int deltaX, int deltaY) {
    knots[knotNumber] = knots[knotNumber].plus(deltaX, deltaY);
    Logger.info("  Knot[" + knotNumber + "] at " + knots[knotNumber]);
    if (isTail(knotNumber)) {
      field.registerTailPosition(knots[knotNumber]);
    }
  }

  private boolean isTail(int knotNumber) {
    return knotNumber == numberOfKnots - 1;
  }

  /**
   * Print the current knot map. Used for debugging.
   */
  public void printKnotMap() {
    KnotDebugMap map = new KnotDebugMap(findKnotPositionBoundaries());
    for (int i = 0; i < numberOfKnots; ++i) {
      map.addKnot(i, knots[i]);
    }
    // Add the head on top of all others
    map.addKnot(0, knots[0]);
    map.print();
  }

  /**
   * Find boundaries of the area covering all the knots.
   *
   * @return The boundaries covering all knot positions
   */
  public Rectangle findKnotPositionBoundaries() {
    int minX = 10000000;
    int minY = 10000000;
    int maxX = -10000000;
    int maxY = -10000000;
    for (int i = 0; i < numberOfKnots; ++i) {
      minX = Math.min(knots[i].getX(), minX);
      minY = Math.min(knots[i].getY(), minY);
      maxX = Math.max(knots[i].getX(), maxX);
      maxY = Math.max(knots[i].getY(), maxY);
    }
    return new Rectangle(minX, minY, maxX, maxY);
  }

  /**
   * Get the position of a knot.
   *
   * @param knotNumber The number of the knot. Numbering starts at zero!
   * @return The knot position
   * @throws ArrayIndexOutOfBoundsException if the knotNumber is invalid
   */
  public Vector getKnot(int knotNumber) {
    return knots[knotNumber];
  }
}
