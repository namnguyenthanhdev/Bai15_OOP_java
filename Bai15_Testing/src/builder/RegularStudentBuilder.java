package builder;

import dto.SemesterResult;
import dto.student.RegularStudent;
import java.util.List;

public class RegularStudentBuilder {

  private String id;
  private float entryScore;
  private List<SemesterResult> semesterResults;

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

  public RegularStudent build() {
    return new RegularStudent(this.id, this.entryScore, this.semesterResults);
  }
}
