package dto.student;

import dto.SemesterResult;
import exception.InvalidStudentIdException;
import java.util.ArrayList;
import java.util.List;
import util.ValidatorUtil;

public abstract class BaseStudent {

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

  private String id;
  private float entryScore;

  private List<SemesterResult> semesterResults = new ArrayList<>();

  public BaseStudent(String id, float entryScore, List<SemesterResult> semesterResults) {
    this.id = id;
    setEntryScore(entryScore);
    this.semesterResults = semesterResults;
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
    this.semesterResults = semesterResults;
  }
}
