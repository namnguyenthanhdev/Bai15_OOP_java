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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import service.semester.SemesterServiceInterface;


public class StudentManagerInterfaceBasicImplement implements StudentManagerInterface {

  private final Map<String, BaseStudent> studentMap = new HashMap<>();

  private SemesterServiceInterface semesterService;

  private List<String> regularStudentIds = new ArrayList<>();

  private List<String> inServiceStudents = new ArrayList<>();

  private Map<DepartmentType, List<String>> studentIdByDepartmentTypeMap = new HashMap<>();


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
    if (newStudent.getDepartmentType() == null) {
      throw new InvalidStudentTypeException();
    }
    studentMap.put(newStudent.getId(), newStudent);
    addStudentIntoDepartmentTypeMap(newStudent.getId(), newStudent.getDepartmentType());
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
    //CACH 1
//    return getListStudentInDepartmentType(departmentType).stream().filter(
//        student -> student.getSemesterResults().get(student.getSemesterResults().size() -1).getAverage() > 8).collect(
//        Collectors.toList());
    //CACH 2
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)) {
      return null;
    }
    List<BaseStudent> results = new ArrayList<>();

    studentIdByDepartmentTypeMap.get(departmentType).forEach(
        studentId -> {
          BaseStudent student = getStudentFromMap(studentId);
          if (student.getSemesterResults().get(student.getSemesterResults().size() - 1).getAverage() > 8) {
            results.add(student);
          }
        }
    );
    return results;
  }

  @Override
  public List<BaseStudent> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType) {
    List<BaseStudent> maxScoreStudents = new ArrayList<>();
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)) {
      return null;
    }
    float currentMaxScore = 0;
    for (String studentId : studentIdByDepartmentTypeMap.get(departmentType)) {
      BaseStudent student = studentMap.get(studentId);
      if (currentMaxScore < student.getMaxAverageScoreOfStudent()) {
        currentMaxScore = student.getMaxAverageScoreOfStudent();
        maxScoreStudents = Collections.singletonList(student);
      } else if (currentMaxScore == student.getMaxAverageScoreOfStudent()) {
        maxScoreStudents.add(student);
      }

    }
    return maxScoreStudents;
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

  private List<BaseStudent> getListStudentInDepartmentType(DepartmentType departmentType) {
    return studentMap.values().stream().filter(student -> student.getDepartmentType() == departmentType).collect(
        Collectors.toList());
  }

  private void addStudentIntoDepartmentTypeMap(String studentId, DepartmentType departmentType) {
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)) {
      studentIdByDepartmentTypeMap.put(departmentType, new ArrayList<>(Arrays.asList(studentId)));
      return;
    }
    if (studentIdByDepartmentTypeMap.get(departmentType) == null) {
      studentIdByDepartmentTypeMap.put(departmentType, new ArrayList<>(Arrays.asList(studentId)));
      return;
    }
    studentIdByDepartmentTypeMap.get(departmentType).add(studentId);

  }
}
