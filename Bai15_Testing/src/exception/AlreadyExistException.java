package exception;

public class AlreadyExistException extends RuntimeException {

  public AlreadyExistException(String id) {

    super(id);
  }

}
