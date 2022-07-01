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

  private static final String STUDENT_ID_PREFIX = "STD_";
  private static int currentStudentIdGenerator = 0;
  private String id;
  private String name;
  private float entryScore;
  private Year entryYear;
  private DepartmentType departmentType;
  private List<SemesterResult> semesterResults = new ArrayList<>();

  public BaseStudent(String id, String name, float entryScore, Year entryYear,
      DepartmentType departmentType, List<SemesterResult> semesterResults) {
    this.id = id;
    setName(name);
    setEntryScore(entryScore);
    setEntryYear(entryYear);
    setDepartmentType(departmentType);
    setSemesterResults(semesterResults);
  }

  protected static String generateNewStudentId() {
    currentStudentIdGenerator += 1;
    return STUDENT_ID_PREFIX + currentStudentIdGenerator;
  }

  public static void checkValidId(String id) {
    if (id == null || !id.startsWith(STUDENT_ID_PREFIX)) {
      throw new InvalidStudentIdException(id);
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getEntryScore() {
    return entryScore;
  }

  public void setEntryScore(float entryScore) {
    if (ValidatorUtil.checkValidScore(entryScore)) {
      this.entryScore = entryScore;
    }
  }

  public Year getEntryYear() {
    return entryYear;
  }

  public void setEntryYear(Year entryYear) {
    if (ValidatorUtil.checkValidEntryYear(entryYear)) {
      this.entryYear = entryYear;
    }
  }

  public DepartmentType getDepartmentType() {
    return departmentType;
  }

  public void setDepartmentType(DepartmentType departmentType) {
    this.departmentType = departmentType;
  }

  public List<SemesterResult> getSemesterResults() {
    return semesterResults;
  }

  public void setSemesterResults(List<SemesterResult> semesterResultList) {
    if (semesterResults == null) {
      this.semesterResults = new ArrayList<>();
    } else {
      this.semesterResults = semesterResultList;
    }
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
