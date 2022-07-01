package service.student;

import dto.SemesterResult;
import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import dto.student.RegularStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import exception.AlreadyExistException;
import exception.InvalidStudentTypeException;
import exception.LackOfDepartmentTypeException;
import exception.ResultNotFoundException;
import exception.StudentNotFoundException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import service.inServicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceInterface;

public class StudentManagerImplements implements StudentManagerInterface {

  private static final int MAX_AVERAGE_SCORE = 8;
  private Map<String, BaseStudent> studentMap = new HashMap<>();
  private Map<DepartmentType, List<String>> studentIdByDepartmentTypeMap = new HashMap<>();
  private List<String> regularStudentIds = new ArrayList<>();
  private List<String> inServiceStudentIds = new ArrayList<>();
  private SemesterServiceInterface semesterService;
  private InServicePlaceInterface inServicePlaceService;

  public StudentManagerImplements(SemesterServiceInterface semesterService,
      InServicePlaceInterface inServicePlaceService) {
    this.semesterService = semesterService;
    this.inServicePlaceService = inServicePlaceService;
  }

  @Override
  public boolean isRegularStudent(String studentId) {
    BaseStudent student = studentMap.get(studentId);
    return checkStudentType(student) == StudentType.REGULAR;
  }

  @Override
  public BaseStudent addNewStudent(BaseStudent baseStudent) {
    StudentType studentType = checkStudentType(baseStudent);
    BaseStudent newStudent = null;
    switch (studentType) {
      case IN_SERVICE:
        newStudent = InServiceStudent.createNewStudent((InServiceStudent) baseStudent);
        inServiceStudentIds.add(newStudent.getId());
        break;
      case REGULAR:
        newStudent = RegularStudent.createNewStudent((RegularStudent) baseStudent);
        regularStudentIds.add(newStudent.getId());
        break;
      default: {
      }
    }
    if (studentMap.containsKey(newStudent.getId())) {
      throw new AlreadyExistException(newStudent.getId());
    }
    //why we need to use baseStudent here instead of newStudent
    baseStudent.getSemesterResults().forEach(semesterResult -> {
      semesterService.checkValidSemester(semesterResult.getSemesterType(),
          semesterResult.getYear());
    });
//    newStudent.getSemesterResults().forEach(semesterResult -> {
//        semesterService.checkValidSemester(semesterResult.getSemesterType(), semesterResult.getYear());
//    });
    BaseStudent.checkValidId(newStudent.getId());
    if (newStudent.getDepartmentType() == null) {
      throw new LackOfDepartmentTypeException();
    }
    studentMap.put(newStudent.getId(), newStudent);
    addStudentIntoDepartmentTypeMap(newStudent.getId(), newStudent.getDepartmentType());
    return newStudent;
  }

  @Override
  public float getAverageScoreOfStudentBySemester(String studentId, Year year,
      SemesterType semesterType) {
    BaseStudent baseStudent = getStudentFromMap(studentId);
    List<SemesterResult> results = baseStudent.getSemesterResults();
    if (results == null || results.isEmpty()) {
      throw new ResultNotFoundException();
    }
    Float averageScore = null;
    for (SemesterResult result : results) {
      if (result.getSemesterType().equals(semesterType) && result.getYear().equals(year)) {
        averageScore = result.getAverage();
        break;
      }
    }
    if (averageScore == null) {
      throw new ResultNotFoundException();
    }
    return averageScore;
  }

  @Override
  public int getTotalRegularStudentByDepartment(DepartmentType departmentType) {
    return regularStudentIds.size();
  }

  @Override
  public List<InServiceStudent> getInServiceStudentListByDepartmentTypeAndInServicePlace(
      DepartmentType departmentType, String inServiceAddress) {
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)
        || studentIdByDepartmentTypeMap.get(departmentType) == null) {
      return null;
    }
    List<InServiceStudent> inServiceStudents = new ArrayList<>();
    studentIdByDepartmentTypeMap.get(departmentType).forEach(studentId -> {
      BaseStudent student = getStudentFromMap(studentId);
      if (student instanceof InServiceStudent) {
        InServiceStudent inServiceStudent = (InServiceStudent) student;
        if (inServicePlaceService.getPlace(inServiceStudent.getInServicePlaceId()).getAddress()
            .equals(inServiceAddress)) {
          inServiceStudents.add(inServiceStudent);
        }
      }
    });
    return inServiceStudents;
  }

  @Override
  public List<BaseStudent> getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(
      DepartmentType departmentType) {
    List<BaseStudent> higherThanEights = new ArrayList<>();
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)
        || studentIdByDepartmentTypeMap.get(departmentType) == null) {
      return null;
    }
    studentIdByDepartmentTypeMap.get(departmentType).forEach(studentId -> {
      BaseStudent baseStudent = getStudentFromMap(studentId);
      if (baseStudent.getSemesterResults().get(baseStudent.getSemesterResults().size() - 1)
          .getAverage() > MAX_AVERAGE_SCORE) {
        higherThanEights.add(baseStudent);
      }
    });
    return higherThanEights;
  }

  @Override
  public List<BaseStudent> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType) {
    List<BaseStudent> maxScoreStudents = new ArrayList<>();
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType)
        || studentIdByDepartmentTypeMap.get(departmentType) == null) {
      return null;
    }
    float currentMaxScore = 0;
    for (String studentId : studentIdByDepartmentTypeMap.get(departmentType)) {
      BaseStudent baseStudent = getStudentFromMap(studentId);
      if (currentMaxScore < baseStudent.getMaxAverageScoreOfStudent()) {
        currentMaxScore = baseStudent.getMaxAverageScoreOfStudent();
        maxScoreStudents = new ArrayList<>(Arrays.asList(baseStudent));
      } else if (currentMaxScore == baseStudent.getMaxAverageScoreOfStudent()) {
        maxScoreStudents.add(baseStudent);
      }
    }

    return maxScoreStudents;
  }

  @Override
  public List<BaseStudent> sortStudentAscendingByScoreAndDescendingByYear(
      DepartmentType departmentType) {
    return getBaseStudentList(departmentType).stream()
        .sorted(Comparator.comparingDouble(BaseStudent::getEntryScore)
            .thenComparing(BaseStudent::getEntryYear).reversed()).collect(
            Collectors.toList());
  }

  private BaseStudent getStudentFromMap(String studentId) {
    if (!studentMap.containsKey(studentId) || studentMap.get(studentId) == null) {
      throw new StudentNotFoundException();
    }
    return studentMap.get(studentId);
  }

  private List<BaseStudent> getBaseStudentList(DepartmentType departmentType) {
    return studentMap.values().stream()
        .filter(student -> student.getDepartmentType().equals(departmentType)).collect(
            Collectors.toList());
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

  private void addStudentIntoDepartmentTypeMap(String studentId, DepartmentType departmentType) {
    if (!studentIdByDepartmentTypeMap.containsKey(departmentType) //non-existed department type
        || studentIdByDepartmentTypeMap.get(departmentType) == null) { //non-existed student id list
      studentIdByDepartmentTypeMap.put(departmentType, new ArrayList<>(Arrays.asList(studentId)));
    }
    studentIdByDepartmentTypeMap.get(departmentType).add(studentId);
  }

}
