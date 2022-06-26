package exception;

public class InServicePlaceNotFoundException extends RuntimeException {

  public InServicePlaceNotFoundException(String id) {

    super(id);
  }

}
