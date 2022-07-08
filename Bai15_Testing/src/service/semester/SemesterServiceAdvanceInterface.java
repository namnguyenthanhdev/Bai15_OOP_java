package service.semester;

import dto.SemesterResult;
import dto.student.BaseStudent;
import entity.SemesterResultEntity;
import enumeration.SemesterType;
import java.sql.SQLException;
import java.time.Year;

public interface SemesterServiceAdvanceInterface {

  void checkValidSemester(SemesterType semesterType, Year year);
  SemesterResult addNewSemesterResult(String studentId, SemesterResult semesterResult) throws SQLException;
}
