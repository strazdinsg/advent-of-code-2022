package problem.day10;

/**
 * Represents a CRT screen according to the task logic. Can draw an image frame pixel by pixel
 * based on the current sprite position (the CPU register value).
 */
public class CrtScreen {
  private static final int SCREEN_WIDTH = 40;
  private static final char FILLED_PIXEL = '#';
  private static final char EMPTY_PIXEL = '.';
  private final CentralProcessorUnit cpu;
  private int pixelPosition;

  /**
   * Create a screen.
   *
   * @param cpu The CPU to which the screen is connected
   */
  public CrtScreen(CentralProcessorUnit cpu) {
    this.cpu = cpu;
    pixelPosition = 0;
  }

  /**
   * Draw a pixel on the current screen position and advance the cursors to the next position.
   * The pixel value to be drawn depends on the CPU register value.
   */
  public void drawPixel() {
    printPixel(getPixelValue());
    pixelPosition++;
    if (pixelPosition == SCREEN_WIDTH) {
      pixelPosition = 0;
      moveCursorToNewLine();
    }
  }

  private char getPixelValue() {
    int spritePosition = cpu.getRegisterValue();
    return isPixelWithinSprite(spritePosition) ? FILLED_PIXEL : EMPTY_PIXEL;
  }

  private boolean isPixelWithinSprite(int spritePosition) {
    return Math.abs(spritePosition - pixelPosition) <= 1;
  }

  private void printPixel(char pixel) {
    System.out.print(pixel);
  }

  private void moveCursorToNewLine() {
    System.out.println();
  }
}
