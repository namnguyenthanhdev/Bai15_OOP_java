package exception;

public class InvalidSemesterKeyIdException extends RuntimeException {

  public InvalidSemesterKeyIdException(String s) {
    super("Invalid key id for semester. Current id -> " + s);
  }
}
