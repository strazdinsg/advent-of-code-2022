package problem.day15;

import tools.IntegerRange;
import tools.Vector;

/**
 * Represents a single sensor - it's location and the location of a closest beacon.
 */
public class Sensor {
  private final Vector sensorPosition;
  private final Vector closestBeaconPosition;

  private final int cleanRadius;

  /**
   * Create a new sensor from a string.
   *
   * @param inputFileLine The string red from the input file
   */
  public Sensor(String inputFileLine) {
    String[] parts = inputFileLine.split(": ");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid input file format: " + inputFileLine);
    }
    String sensorInfo = parts[0];
    String beaconInfo = parts[1];
    sensorPosition = parseSensorPosition(sensorInfo);
    closestBeaconPosition = parseBeaconPosition(beaconInfo);
    cleanRadius = calculateCleanRadius();
  }

  private Vector parseSensorPosition(String sensorInfo) throws IllegalArgumentException {
    if (!sensorInfo.startsWith("Sensor at x=")) {
      throw new IllegalArgumentException("Invalid sensor info format: " + sensorInfo);
    }
    return parseCoordinates(sensorInfo.substring(12));
  }

  private Vector parseCoordinates(String xyString) {
    String[] parts = xyString.split(", y=");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Invalid coordinate format: " + xyString);
    }

    Vector result;
    try {
      result = new Vector(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid integer format in sensor info: "
          + e.getMessage());
    }

    return result;
  }

  private Vector parseBeaconPosition(String beaconInfo) throws IllegalArgumentException {
    if (!beaconInfo.startsWith("closest beacon is at x=")) {
      throw new IllegalArgumentException("Invalid beacon info format: " + beaconInfo);
    }
    return parseCoordinates(beaconInfo.substring(23));
  }

  private int calculateCleanRadius() {
    Vector manhattanDistance = closestBeaconPosition.minus(sensorPosition);
    return manhattanDistance.getAbsoluteX() + manhattanDistance.getAbsoluteY();
  }

  /**
   * Find a clean line (range of horizontal cells) where no beacon can be placed.
   *
   * @param rowIndex The row too look at
   * @return An integer range representing the range of columns where no beacon can be located.
   *     Returns null if this sensor is too far from the given row to say anything about
   *     beacons there.
   */
  public IntegerRange findCleanLine(int rowIndex) {
    int verticalDistance = Math.abs(rowIndex - sensorPosition.getY());
    int horizontalCleanRadius = cleanRadius - verticalDistance;

    IntegerRange result = null;
    if (horizontalCleanRadius >= 0) {
      int fromX = sensorPosition.getX() - horizontalCleanRadius;
      int toX = sensorPosition.getX() + horizontalCleanRadius;
      result = new IntegerRange(fromX, toX);
    }

    return result;
  }

  public Vector getClosestBeaconPosition() {
    return closestBeaconPosition;
  }
}
