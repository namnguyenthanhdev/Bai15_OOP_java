package builder;

import dto.SemesterResult;
import dto.student.RegularStudent;
import enumeration.DepartmentType;
import java.util.List;

public class RegularStudentBuilder {

  private String id;
  private float entryScore;
  private List<SemesterResult> semesterResults;

  private DepartmentType departmentType;

  public RegularStudentBuilder setId(String id) {
    this.id = id;
    return this;
  }


  public RegularStudentBuilder setSemesterResults(List<SemesterResult> semesterResults) {
    this.semesterResults = semesterResults;
    return this;
  }

  public RegularStudentBuilder setEntryScore(float entryScore) {
    this.entryScore = entryScore;
    return this;
  }

  public RegularStudentBuilder setDepartmentType(DepartmentType departmentType) {
    this.departmentType = departmentType;
    return this;
  }

  public RegularStudent build() {
    return new RegularStudent(this.id, this.entryScore, this.semesterResults, this.departmentType);
  }
}
