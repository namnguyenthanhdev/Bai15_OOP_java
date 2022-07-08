package service.student;

import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

public interface StudentManagerAdvanceInterface {

  boolean isRegularStudent(String studentId);

  BaseStudent addNewStudent(BaseStudent baseStudent) throws SQLException;

  double getAverageScoreOfStudentBySemester(String studentId, Year year, SemesterType semesterType)
      throws SQLException;

  int getTotalRegularStudentByDepartment(DepartmentType departmentType);

  List<String> getInServiceStudentListByDepartmentTypeAndInServicePlace(StudentType studentType,
      DepartmentType departmentType, String inServiceAddress) throws SQLException;

  List<String> getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType departmentType) throws SQLException;

  List<String> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType)
      throws SQLException;

  List<String> sortStudentAscendingByScoreAndDescendingByYear(DepartmentType departmentType)
      throws SQLException;

}
