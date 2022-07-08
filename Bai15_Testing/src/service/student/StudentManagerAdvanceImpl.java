package service.student;

import dto.student.BaseStudent;
import dto.student.InServiceStudent;
import dto.student.RegularStudent;
import entity.InServicePlaceEntity;
import entity.StudentEntity;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import exception.AlreadyExistException;
import exception.InvalidStudentTypeException;
import exception.LackOfDepartmentTypeException;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import repository.student.StudentRepository;
import service.inServicePlace.InServicePlaceAdvanceImpl;
import service.inServicePlace.InServicePlaceAdvanceInterface;
import service.inServicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceAdvanceInterface;
import service.semester.SemesterServiceInterface;

public class StudentManagerAdvanceImpl implements StudentManagerAdvanceInterface {


  private SemesterServiceAdvanceInterface semesterService;
  private InServicePlaceAdvanceInterface inServicePlaceAdvanceService;
  private StudentRepository studentRepository;

  public StudentManagerAdvanceImpl(SemesterServiceAdvanceInterface semesterService,
      InServicePlaceAdvanceInterface inServicePlaceAdvanceService, StudentRepository studentRepository) {
    this.semesterService = semesterService;
    this.inServicePlaceAdvanceService = inServicePlaceAdvanceService;
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
  public double getAverageScoreOfStudentBySemester(String studentId, Year year, SemesterType semesterType)
      throws SQLException {
    BaseStudent.checkValidId(studentId);
    semesterService.checkValidSemester(semesterType, year);
    return  studentRepository.getAverageScoreById(studentId, String.valueOf(semesterType),
        year.getValue());
  }

  @Override
  public int getTotalRegularStudentByDepartment(DepartmentType departmentType) {
    return 0;
  }

  @Override
  public List<String> getInServiceStudentListByDepartmentTypeAndInServicePlace(StudentType studentType, DepartmentType departmentType,
      String inServiceAddress) throws SQLException {
    if (departmentType == null || studentType == null || inServiceAddress == null){
      throw new LackOfDepartmentTypeException();
    }
//    inServicePlaceAdvanceService.getPlace(inServiceAddress);
    return studentRepository.getStudentByDepartmentTypeAndStudentTypeAndAddress(
            String.valueOf(studentType), String.valueOf(departmentType), inServiceAddress);

  }

  @Override
  public List<String> getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType departmentType)
      throws SQLException {
    return studentRepository.getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(departmentType);
  }

  @Override
  public List<String> getMaxAverageScoreStudentFromAllSemester(DepartmentType departmentType)
      throws SQLException {
    return studentRepository.getMaxAverageScoreStudentFromAllSemester(departmentType);
  }

  @Override
  public List<String> sortStudentAscendingByScoreAndDescendingByYear(DepartmentType departmentType)
      throws SQLException {
    return studentRepository.sortStudentAscendingByScoreAndDescendingByYear(departmentType);
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
