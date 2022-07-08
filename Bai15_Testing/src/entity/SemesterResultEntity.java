package entity;

import dto.SemesterResult;
import enumeration.SemesterType;

public class SemesterResultEntity {
  private String student_id;
  private String semester_type;
  private int year;
  private double average_score;

  public SemesterResultEntity() {
  }

  public SemesterResultEntity(String student_id,SemesterResult newSemesterResult) {
    this.student_id = student_id;
    setSemester_type(String.valueOf(newSemesterResult.getSemesterType()));
    setYear(newSemesterResult.getYear().getValue());
    setAverage_score(newSemesterResult.getAverage());
  }

  public String getStudent_id() {
    return student_id;
  }

  public void setStudent_id(String student_id) {
    this.student_id = student_id;
  }

  public String getSemester_type() {
    return semester_type;
  }

  public void setSemester_type(String semester_type) {
    this.semester_type = semester_type;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public double getAverage_score() {
    return average_score;
  }

  public void setAverage_score(double average_score) {
    this.average_score = average_score;
  }
}
