package builder;

import dto.SemesterResult;
import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class InServiceStudentBuilder {

  private String id;
  private String name;
  private float entryScore;
  private Year entryYear;
  private DepartmentType departmentType;
  private List<SemesterResult> semesterResults = new ArrayList<>();
  private String placeId;

  public InServiceStudentBuilder setId(String id) {
    this.id = id;
    return this;
  }

  public InServiceStudentBuilder setName(String name) {
    this.name = name;
    return this;
  }

  public InServiceStudentBuilder setEntryScore(float entryScore) {
    this.entryScore = entryScore;
    return this;
  }

  public InServiceStudentBuilder setEntryYear(Year entryYear) {
    this.entryYear = entryYear;
    return this;
  }

  public InServiceStudentBuilder setDepartmentType(DepartmentType departmentType) {
    this.departmentType = departmentType;
    return this;
  }

  public InServiceStudentBuilder setSemesterResults(List<SemesterResult> semesterResults) {
    this.semesterResults = semesterResults;
    return this;
  }

  public InServiceStudentBuilder setInServicePlaceId(String placeId) {
    this.placeId = placeId;
    return this;
  }

  public InServiceStudent build() {
    return new InServiceStudent(this.id, this.name, this.entryScore, this.entryYear,
        this.departmentType, this.semesterResults, this.placeId);
  }
}
