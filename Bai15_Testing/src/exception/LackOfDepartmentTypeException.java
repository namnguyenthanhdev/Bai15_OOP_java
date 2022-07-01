package exception;

public class LackOfDepartmentTypeException extends RuntimeException {

  public LackOfDepartmentTypeException() {
    super("Invalid type of department.");
  }

}
