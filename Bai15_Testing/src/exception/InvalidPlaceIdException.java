package exception;

public class InvalidPlaceIdException extends RuntimeException {

  public InvalidPlaceIdException(String id) {
    super(id);
  }
}
