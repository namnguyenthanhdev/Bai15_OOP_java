package exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException() {
    super("Student can not be found.");
  }

}
