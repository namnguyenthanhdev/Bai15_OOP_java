package service.student;

import dto.InServicePlace;
import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import java.time.Year;
import java.util.List;

public interface StudentManagerInterface {

  boolean isRegularStudent(String studentId);

  //TODO: remove after test
  void printMapStudents();

  BaseStudent addNewStudent(BaseStudent baseStudent);

  float getAverageScoreOfStudentBySemester(String studentId, Year year, SemesterType semesterType);

  int getTotalRegularStudentByDepartment(DepartmentType departmentType);

  List<InServiceStudent> getInServiceStudentListByDepartmentTypeAndInServicePlace(
      DepartmentType departmentType,
      InServicePlace inServicePlace);

  List<BaseStudent> getAverageScoreBiggerThanEightInRecentSemesterByDepartmentType(DepartmentType departmentType);

  List<BaseStudent> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType);

  List<BaseStudent> sortStudentAscendingByScoreAndDescendingByYear(DepartmentType departmentType);


}
