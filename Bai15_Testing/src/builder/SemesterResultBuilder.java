package builder;

import dto.SemesterResult;
import enumeration.SemesterType;
import java.time.Year;

public class SemesterResultBuilder {

  private SemesterType semester;

  private Year year;
  private float average;


  public SemesterResultBuilder setSemesterBuilder(SemesterType semester) {
    this.semester = semester;
    return this;
  }

  public SemesterResultBuilder setYear(Year year) {
    this.year = year;
    return this;
  }


  public SemesterResultBuilder setAverageBuilder(float average) {
    this.average = average;
    return this;
  }


  public SemesterResult build() {
    return new SemesterResult(this.semester, this.year, this.average);
  }

}
