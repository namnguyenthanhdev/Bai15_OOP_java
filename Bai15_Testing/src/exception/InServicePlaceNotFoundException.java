package exception;

public class InServicePlaceNotFoundException extends RuntimeException {

  public InServicePlaceNotFoundException() {
    super("In service place can not be found. Current id");
  }
}
