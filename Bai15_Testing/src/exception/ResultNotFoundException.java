package exception;

public class ResultNotFoundException extends RuntimeException {

  public ResultNotFoundException() {
    super("Result can not found.");
  }

}
