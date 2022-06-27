package dto;

import enumeration.SemesterType;
import util.ValidatorUtil;

import java.time.Year;

public class SemesterResult {

  private SemesterType semester;

  private Year year;
  private float average;

  public SemesterResult(SemesterType semester, Year year, float average) {
    setSemester(semester);
    setYear(year);
    setAverage(average);
  }

  public SemesterType getSemester() {

    return semester;
  }

  public void setSemester(SemesterType semester) {

    this.semester = semester;
  }

  public float getAverage() {

    return average;
  }

  public void setAverage(float average) {
    if (ValidatorUtil.checkValidScore(average)){
      this.average = average;
    }
  }


  @Override
  public String toString() {
    return "Result{" + "semester=" + semester +
            ", year=" + year + ", average=" + average + '}';
  }

  public Year getYear() {
    return year;
  }

  public void setYear(Year year) {
    this.year = year;
  }
}
