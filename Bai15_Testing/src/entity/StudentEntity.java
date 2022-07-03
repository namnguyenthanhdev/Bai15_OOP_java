package entity;

import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import enumeration.StudentType;
import java.time.Year;
import util.ValidatorUtil;

public class StudentEntity {

  private String student_id;

  private String student_type;

  private String name;
  private float entry_score;
  private int entry_year;
  private String department_type;

  private String in_service_place_id;

  public StudentEntity() {
  }

  public StudentEntity(InServiceStudent inServiceStudent) {
    setStudentType(StudentType.IN_SERVICE);
    setStudentId(inServiceStudent.getId());
    setEntryScore(inServiceStudent.getEntryScore());
    setName(inServiceStudent.getName());
    setEntryYear(inServiceStudent.getEntryYear());
    setDepartmentType(inServiceStudent.getDepartmentType());
    setInServicePlaceId(inServiceStudent.getInServicePlaceId());
  }

  public void setStudentType(StudentType studentType) {
    this.student_type = studentType.name();
  }

  public String getStudentType() {
    return this.student_type;
  }

  public String getStudentId() {
    return student_id;
  }

  public void setStudentId(String id) {
    this.student_id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getEntryScore() {
    return entry_score;
  }

  public void setEntryScore(float entryScore) {
    if (ValidatorUtil.checkValidScore(entryScore)) {
      this.entry_score = entryScore;
    }
  }

  public int getEntryYear() {
    return entry_year;
  }

  public void setEntryYear(Year entryYear) {
    this.entry_year = entryYear.getValue();
  }

  public String getDepartmentType() {

    return department_type;
  }

  public void setDepartmentType(DepartmentType departmentType) {

    this.department_type = departmentType.name();
  }


  public String getInServicePlaceId() {
    return in_service_place_id;
  }

  public void setInServicePlaceId(String in_service_place_id) {
    this.in_service_place_id = in_service_place_id;
  }
}
