package dto.student;

import dto.SemesterResult;
import enumeration.DepartmentType;
import exception.InvalidStudentIdException;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import util.ValidatorUtil;

public abstract class BaseStudent {


  private String id;
  private float entryScore;
  private DepartmentType departmentType;

  private Year entryYear;

  private List<SemesterResult> semesterResults = new ArrayList<>();


  private static int currentStudentIdGenerator = 0;

  private static final String STUDENT_ID_PREFIX = "STD_";


  protected static String generateNewStudentId() {
    currentStudentIdGenerator += 1;
    return STUDENT_ID_PREFIX + currentStudentIdGenerator;
  }

  public static void checkValidId(String id) {
    if (id == null || !id.startsWith(STUDENT_ID_PREFIX)) {
      throw new InvalidStudentIdException(id);
    }
  }


  public BaseStudent(String id, float entryScore, List<SemesterResult> semesterResults, DepartmentType departmentType, Year entryYear) {
    this.id = id;
    setEntryYear(entryYear);
    setEntryScore(entryScore);
    setSemesterResults(semesterResults);
    setDepartmentType(departmentType);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public float getEntryScore() {
    return entryScore;
  }

  public void setEntryScore(float entryScore) {
    if (ValidatorUtil.checkValidScore(entryScore)) {
      this.entryScore = entryScore;
    }
  }

  public List<SemesterResult> getSemesterResults() {
    return semesterResults;
  }

  public void setSemesterResults(List<SemesterResult> semesterResults) {
    if (semesterResults == null) {
      this.semesterResults = new ArrayList<>();
    } else {
      this.semesterResults = semesterResults;
    }
  }

  public DepartmentType getDepartmentType() {
    return departmentType;
  }

  public void setDepartmentType(DepartmentType departmentType) {
    this.departmentType = departmentType;
  }

  public Year getEntryYear() {
    return entryYear;
  }
  public void setEntryYear(Year entryYear) {
    this.entryYear = entryYear;
  }
  public float getMaxAverageScoreOfStudent() {
    AtomicReference<Float> maxScore = new AtomicReference<>((float) 0);
    semesterResults.forEach(semesterResult -> {
      if (maxScore.get() < semesterResult.getAverage()) {
        maxScore.set(semesterResult.getAverage());
      }
    });
    return maxScore.get();
  }
}
