package exception;

public class InvalidScoreException extends RuntimeException {

  public InvalidScoreException(float score) {

    super("Invalid score range (must be between 0 - 10) -> actual value:" + score);
  }

}
