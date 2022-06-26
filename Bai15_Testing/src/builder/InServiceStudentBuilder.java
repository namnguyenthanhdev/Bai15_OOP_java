package builder;

import dto.SemesterResult;
import dto.student.InServiceStudent;
import dto.student.RegularStudent;
import java.util.List;

public class InServiceStudentBuilder {

  private String id;
  private float entryScore;
  private List<SemesterResult> semesterResults;

  private String inServicePlaceId;

  public InServiceStudentBuilder setId(String id) {
    this.id = id;
    return this;
  }


  public InServiceStudentBuilder setSemesterResults(List<SemesterResult> semesterResults) {
    this.semesterResults = semesterResults;
    return this;
  }

  public InServiceStudentBuilder setEntryScore(float entryScore) {
    this.entryScore = entryScore;
    return this;
  }


  public InServiceStudentBuilder setInServicePlaceId(String inServicePlaceId) {
    this.inServicePlaceId = inServicePlaceId;
    return this;
  }

  public InServiceStudent build() {
    return new InServiceStudent(this.id, this.entryScore, this.semesterResults, this.inServicePlaceId);
  }
}
