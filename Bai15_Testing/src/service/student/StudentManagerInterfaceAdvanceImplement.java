package service.student;

import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import java.time.Year;
import java.util.List;


public class StudentManagerInterfaceAdvanceImplement implements StudentManagerInterface {

  @Override
  public boolean isRegularStudent(String studentId) {
    return false;
  }

  @Override
  public boolean isInServiceStudent(String studentId) {
    return false;
  }

  @Override
  public void printMapStudents() {

  }

  @Override
  public BaseStudent addNewStudent(BaseStudent baseStudent) {
    return null;
  }

  @Override
  public float getAverageScoreOfStudentBySemester(String studentId, Year year, SemesterType semesterType) {
    return 0;
  }

  @Override
  public int getTotalRegularStudentByDepartment(DepartmentType departmentType) {
    return 0;
  }

  @Override
  public List<InServiceStudent> getInServiceStudentListByDepartmentTypeAndInServicePlace(DepartmentType departmentType,
      String inServicePlaceAddress) {
    return null;
  }

  @Override
  public List<BaseStudent> getAverageScoreBiggerThanEightInRecentSemesterByDepartmentType(
      DepartmentType departmentType) {
    return null;
  }

  @Override
  public List<BaseStudent> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType) {
    return null;
  }

  @Override
  public List<BaseStudent> sortStudentAscendingByScoreAndDescendingByYear(DepartmentType departmentType) {
    return null;
  }
}
