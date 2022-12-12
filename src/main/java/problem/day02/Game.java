package problem.day02;

/**
 * One match of Rock-Paper-Scissors game.
 */
public class Game {
  private static final int WIN_SCORE = 6;
  private static final int DRAW_SCORE = 3;
  private static final int LOSE_SCORE = 0;

  private final HandType myChoice;
  private final HandType opponentChoice;
  private final GameResultType result;

  private Game(HandType myChoice, HandType opponentChoice) {
    this.myChoice = myChoice;
    this.opponentChoice = opponentChoice;
    this.result = calculateGameResult();
  }

  private GameResultType calculateGameResult() {
    if (myChoice == opponentChoice) {
      return GameResultType.DRAW;
    } else if (myHandBeetsOpponent()) {
      return GameResultType.WIN;
    } else {
      return GameResultType.LOSE;
    }
  }

  private boolean myHandBeetsOpponent() {
    return (myChoice == HandType.ROCK && opponentChoice == HandType.SCISSORS)
        || (myChoice == HandType.PAPER && opponentChoice == HandType.ROCK)
        || (myChoice == HandType.SCISSORS && opponentChoice == HandType.PAPER);
  }


  /**
   * Parse a string representing one game.
   *
   * @param gameString String representing a mage
   * @return The game object
   * @throws IllegalStateException If the gameString is invalid
   */
  public static Game parse(String gameString) throws IllegalArgumentException {
    if (gameString == null || gameString.length() != 3 || gameString.charAt(1) != ' ') {
      throw new IllegalArgumentException("Invalid game string format: " + gameString);
    }
    HandType opponentChoice = parseOpponentChoice(gameString.charAt(0));
    HandType myChoice = parseMyChoice(gameString.charAt(2));
    return new Game(myChoice, opponentChoice);
  }

  /**
   * Get teh score I got for this game.
   *
   * @return The score for this game
   */
  public int getScore() {
    return getScoreForMyChoice() + getGameResultScore();
  }

  private static HandType parseOpponentChoice(char opponentChoice) throws IllegalStateException {
    return switch (opponentChoice) {
      case 'A' -> HandType.ROCK;
      case 'B' -> HandType.PAPER;
      case 'C' -> HandType.SCISSORS;
      default -> throw new IllegalArgumentException("Invalid opponent hand: " + opponentChoice);
    };
  }

  private static HandType parseMyChoice(char myChoice) throws IllegalStateException {
    return switch (myChoice) {
      case 'X' -> HandType.ROCK;
      case 'Y' -> HandType.PAPER;
      case 'Z' -> HandType.SCISSORS;
      default -> throw new IllegalArgumentException("Invalid hand: " + myChoice);
    };
  }

  private int getGameResultScore() {
    return switch (result) {
      case WIN -> WIN_SCORE;
      case LOSE -> LOSE_SCORE;
      case DRAW -> DRAW_SCORE;
    };
  }

  private int getScoreForMyChoice() {
    return switch (myChoice) {
      case ROCK -> 1;
      case PAPER -> 2;
      case SCISSORS -> 3;
    };
  }
}
