package exception;

public class InvalidScoreException extends RuntimeException {

  public InvalidScoreException(float score) {
    super("Invalid score range (must be from 0 to 100). Actual score: " + score);
  }

}
