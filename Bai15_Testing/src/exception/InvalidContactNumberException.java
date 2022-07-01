package exception;

public class InvalidContactNumberException extends RuntimeException {

  public InvalidContactNumberException(String contactNumber) {
    super("Invalid phone number -> current number: " + contactNumber);
  }
}
