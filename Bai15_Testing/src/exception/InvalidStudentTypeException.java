package exception;

public class InvalidStudentTypeException extends RuntimeException {

  public InvalidStudentTypeException() {
    super("Invalid student type (must be InServiceStudent or RegularStudent). Current type -> ");
  }

}
