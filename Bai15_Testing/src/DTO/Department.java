package dto;

import com.google.gson.Gson;
import dto.student.BaseStudent;
import enumeration.DepartmentType;
import java.util.List;

public class Department {

  private DepartmentType name;
  private List<BaseStudent> baseStudents;

  public Department(DepartmentType name, List<BaseStudent> baseStudents) {
    this.name = name;
    this.baseStudents = baseStudents;
  }

  public DepartmentType getName() {
    return name;
  }

  public void setName(DepartmentType name) {
    this.name = name;
  }

  public List<BaseStudent> getBaseStudents() {
    return baseStudents;
  }

  public void setBaseStudents(List<BaseStudent> baseStudents) {
    this.baseStudents = baseStudents;
  }

  @Override
  public String toString() {
    return "Department{" +
        "name=" + getName() +
        ", baseStudents=" + new Gson().toJson(getBaseStudents()) +
        '}';
  }
}
