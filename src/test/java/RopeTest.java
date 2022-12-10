import org.junit.jupiter.api.Test;
import problem.day09.InfiniteRopeField;
import problem.day09.Movement;
import problem.day09.Rectangle;
import problem.day09.Rope;
import tools.Vector;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RopeTest {
  @Test
  void testMovement() {
    Rope rope = new Rope(new InfiniteRopeField(), 3);
    rope.move(new Movement("U 2"));
    assertEquals(new Vector(0, -2), rope.getKnot(0));
    assertEquals(new Vector(0, -1), rope.getKnot(1));
    assertEquals(new Vector(0, 0), rope.getKnot(2));
  }

  @Test
  void testBoundaries() {
    Rope rope = new Rope(new InfiniteRopeField(), 3);
    rope.move(new Movement("U 2"));
    Rectangle boundaries = rope.findKnotPositionBoundaries();
    assertEquals(new Vector(0, -2), boundaries.getTopLeft());
    assertEquals(new Vector(0, 0), boundaries.getBottomRight());
    assertEquals(1, boundaries.getWidth());
    assertEquals(3, boundaries.getHeight());
  }

  @Test
  void testDiagonalMoves() {
    // The test from the advent of code: the first 24 moves
    Rope rope = new Rope(new InfiniteRopeField(), 8);
    rope.move(new Movement("U 2"));
    rope.move(new Movement("L 1"));
    rope.move(new Movement("U 1"));
    rope.move(new Movement("D 2"));
    rope.move(new Movement("L 2"));
    rope.move(new Movement("D 1"));
    rope.move(new Movement("L 2"));
    rope.move(new Movement("R 2"));
    rope.move(new Movement("D 2"));
    rope.move(new Movement("U 2"));
    rope.move(new Movement("L 2"));
    rope.move(new Movement("D 1"));
    rope.move(new Movement("L 2"));
    rope.move(new Movement("U 1"));
    rope.move(new Movement("R 2"));
    rope.move(new Movement("D 2"));
    rope.move(new Movement("R 1"));
    rope.move(new Movement("U 1"));
    rope.move(new Movement("R 1"));
    rope.move(new Movement("L 1"));
    rope.move(new Movement("D 2"));
    rope.move(new Movement("R 1"));
    rope.move(new Movement("D 2"));
    rope.move(new Movement("R 2"));
    assertEquals(new Vector(-1, 5), rope.getKnot(0));
    assertEquals(new Vector(-2, 5), rope.getKnot(1));
    assertEquals(new Vector(-2, 4), rope.getKnot(2));
    assertEquals(new Vector(-2, 3), rope.getKnot(3));
    assertEquals(new Vector(-2, 2), rope.getKnot(4));
    assertEquals(new Vector(-2, 1), rope.getKnot(5));
    assertEquals(new Vector(-1, 1), rope.getKnot(6));
    assertEquals(new Vector(0, 0), rope.getKnot(7));
  }
}
