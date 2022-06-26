package service.student;

import com.google.gson.Gson;
import dto.InServicePlace;
import dto.SemesterResult;
import dto.student.BaseStudent;

import dto.student.InServiceStudent;
import dto.student.RegularStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import exception.AlreadyExistException;
import exception.InvalidStudentTypeException;
import exception.NotFoundException;
import exception.StudentNotFoundException;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.semester.SemesterServiceInterface;


public class StudentManagerInterfaceBasicImplement implements StudentManagerInterface {

  private final Map<String, BaseStudent> studentMap = new HashMap<>();

  private SemesterServiceInterface semesterService;

  private List<String> regularStudentIds = new ArrayList<>();

  private List<String> inServiceStudents = new ArrayList<>();

  public StudentManagerInterfaceBasicImplement(SemesterServiceInterface semesterServiceInterface) {
    this.semesterService = semesterServiceInterface;
  }

  @Override
  public boolean isRegularStudent(String studentId) {
    BaseStudent student = getStudentFromMap(studentId);
    return checkStudentType(student) == StudentType.REGULAR;
  }

  @Override
  public void printMapStudents() {
    studentMap.values().forEach(value -> {
      System.out.println(new Gson().toJson(value) + checkStudentType(value).name());
    });
  }

  @Override
  public BaseStudent addNewStudent(BaseStudent dto) {
    StudentType studentType = checkStudentType(dto);
    BaseStudent newStudent = null;
    switch (studentType) {
      case IN_SERVICE:
        newStudent = InServiceStudent.createNewStudent((InServiceStudent) dto);
        break;
      case REGULAR:
        newStudent = RegularStudent.createNewStudent((RegularStudent) dto);
        break;
      default: {
      }
    }
    if (studentMap.containsKey(newStudent.getId())) {
      throw new AlreadyExistException(newStudent.getId());
    }
    dto.getSemesterResults().forEach(semesterResult -> {
      semesterService.checkValidSemester(semesterResult.getSemester(), semesterResult.getYear());
    });
    BaseStudent.checkValidId(newStudent.getId());
    studentMap.put(newStudent.getId(), newStudent);
    return newStudent;

  }

  @Override
  public float getAverageScoreOfStudentBySemester(String studentId, Year year, SemesterType semesterType) {
    BaseStudent student = getStudentFromMap(studentId);
    List<SemesterResult> results = student.getSemesterResults();
    if (results == null || results.isEmpty()) {
      throw new NotFoundException("result not found");
    }
    Float averageScore = null;
    for (SemesterResult result : results) {
      if (result.getSemester() == semesterType && result.getYear().equals(year)) {
        averageScore = result.getAverage();
        break;
      }
    }
    if (averageScore == null) {
      throw new NotFoundException("result not found");
    }
    return averageScore;
  }

  @Override
  public int getTotalRegularStudentByDepartment(DepartmentType departmentType) {
    return 0;
  }

  @Override
  public List<InServiceStudent> getInServiceStudentListByDepartmentTypeAndInServicePlace(DepartmentType departmentType,
      InServicePlace inServicePlace) {
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

  private BaseStudent getStudentFromMap(String studentId) {
    if (!studentMap.containsKey(studentId)) {
      throw new StudentNotFoundException(studentId);
    }
    return studentMap.get(studentId);
  }

  private StudentType checkStudentType(BaseStudent student) {
    if (student instanceof RegularStudent) {
      return StudentType.REGULAR;
    }
    if (student instanceof InServiceStudent) {
      return StudentType.IN_SERVICE;
    }
    throw new InvalidStudentTypeException();
  }
}
