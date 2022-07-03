package service.student;

import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import dto.student.RegularStudent;
import entity.StudentEntity;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import exception.AlreadyExistException;
import exception.InvalidStudentTypeException;
import exception.LackOfDepartmentTypeException;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;
import repository.StudentRepository;
import service.inServicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceInterface;

public class StudentManagerAdvanceImpl implements StudentManagerInterface {


  private SemesterServiceInterface semesterService;
  private InServicePlaceInterface inServicePlaceService;
  private StudentRepository studentRepository;

  public StudentManagerAdvanceImpl(SemesterServiceInterface semesterService,
      InServicePlaceInterface inServicePlaceService, StudentRepository studentRepository) {
    this.semesterService = semesterService;
    this.inServicePlaceService = inServicePlaceService;
    this.studentRepository = studentRepository;
  }

  @Override
  public boolean isRegularStudent(String studentId) {
    return false;
  }

  @Override
  public BaseStudent addNewStudent(BaseStudent baseStudent) throws SQLException {
    StudentType studentType = checkStudentType(baseStudent);
    BaseStudent newStudent = null;
    switch (studentType) {
      case IN_SERVICE:
        newStudent = InServiceStudent.createNewStudent((InServiceStudent) baseStudent);
        validateInServiceStudent((InServiceStudent) newStudent);
        break;
//      case REGULAR:
//        newStudent = RegularStudent.createNewStudent((RegularStudent) baseStudent);
//        break;
      default: {
      }
    }
    if (studentRepository.getStudentById(baseStudent.getId()) != null) {
      throw new AlreadyExistException(newStudent.getId());
    } else {
      studentRepository.insertStudent(new StudentEntity((InServiceStudent) newStudent));
    }
    return newStudent;
  }

  private void validateInServiceStudent(InServiceStudent inServiceStudent) {
    BaseStudent.checkValidId(inServiceStudent.getId());
    if (inServiceStudent.getDepartmentType() == null) {
      throw new LackOfDepartmentTypeException();
    }
    inServiceStudent.getSemesterResults().forEach(semesterResult -> {
      semesterService.checkValidSemester(semesterResult.getSemesterType(),
          semesterResult.getYear());
    });
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
      String inServiceAddress) {
    return null;
  }

  @Override
  public List<BaseStudent> getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(
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
