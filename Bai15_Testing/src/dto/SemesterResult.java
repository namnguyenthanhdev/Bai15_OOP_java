package dto;

import dto.student.BaseStudent;
import enumeration.SemesterType;
import java.time.Year;
import util.ValidatorUtil;

public class SemesterResult {

  private SemesterType semesterType;
  private Year year;
  private float average;
  private BaseStudent baseStudent;

  public SemesterResult(SemesterType semesterType, Year year, float average) {
    this.semesterType = semesterType;
    this.year = year;
    this.average = average;
  }

  public SemesterType getSemesterType() {
    return semesterType;
  }

  public void setSemesterType(SemesterType semesterType) {
    this.semesterType = semesterType;
  }

  public Year getYear() {
    return year;
  }

  public void setYear(Year year) {
    if (ValidatorUtil.checkValidEntryYear(year) || year.isAfter(baseStudent.getEntryYear())) {
      this.year = year;
    }
  }

  public float getAverage() {
    return average;
  }

  public void setAverage(float average) {
    if (ValidatorUtil.checkValidScore(average)) {
      this.average = average;
    }
  }

  @Override
  public String toString() {
    return "SemesterResult{" +
        "semesterType=" + getSemesterType() +
        ", year=" + getYear() +
        ", average=" + getAverage() +
        '}';
  }
}
