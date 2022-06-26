package exception;

public class InvalidStudentIdException extends RuntimeException {

  public InvalidStudentIdException(String studentId) {

    super(studentId);
  }

}
