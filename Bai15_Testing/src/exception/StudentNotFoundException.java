package exception;

public class StudentNotFoundException extends RuntimeException {

  public StudentNotFoundException(String studentId) {

    super(studentId);
  }

}
