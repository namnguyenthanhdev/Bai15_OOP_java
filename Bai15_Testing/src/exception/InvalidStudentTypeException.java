package exception;

public class InvalidStudentTypeException extends RuntimeException {

  public InvalidStudentTypeException() {

    super("Student type is not valid. Must be instance of InServiceStudent or RegularStudent");
  }

}
