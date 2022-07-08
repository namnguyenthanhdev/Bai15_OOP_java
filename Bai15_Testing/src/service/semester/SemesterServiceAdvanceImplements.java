package service.semester;

import dto.SemesterResult;
import dto.student.BaseStudent;
import entity.SemesterResultEntity;
import enumeration.SemesterType;
import exception.AlreadyExistException;
import exception.InvalidSemesterKeyIdException;
import java.sql.SQLException;
import java.time.Year;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import repository.semester.SemesterResultRepository;
import repository.student.StudentRepository;
import service.inServicePlace.InServicePlaceInterface;

public class SemesterServiceAdvanceImplements implements SemesterServiceAdvanceInterface {
  private SemesterServiceAdvanceInterface semesterServiceAdvance;
  private SemesterResultRepository semesterResultRepository;
  private final String DELIMETER = "|";
  private Map<String, Set<Map<SemesterType, Year>>> semesterMap = new HashMap<>();

  public SemesterServiceAdvanceImplements(SemesterResultRepository semesterResultRepository) {
    this.semesterResultRepository = semesterResultRepository;
  }

  public SemesterServiceAdvanceImplements() {
    createSemester(SemesterType.FIRST, Year.of(2010));
    createSemester(SemesterType.FIRST, Year.of(2011));
    createSemester(SemesterType.FIRST, Year.of(2012));
    createSemester(SemesterType.FIRST, Year.of(2013));
    createSemester(SemesterType.FIRST, Year.of(2014));

    createSemester(SemesterType.SECOND, Year.of(2010));
    createSemester(SemesterType.SECOND, Year.of(2011));
    createSemester(SemesterType.SECOND, Year.of(2012));
    createSemester(SemesterType.SECOND, Year.of(2013));
    createSemester(SemesterType.SECOND, Year.of(2014));
  }
  private Set<Map<SemesterType, Year>> createSemester(SemesterType semesterType,
      Year year) { //came from source of school, can not create new
    Set<Map<SemesterType, Year>> newData = new HashSet<>();
    semesterMap.put(buildSemesterKey(semesterType, year), newData);
    return newData;
  }
  private String buildSemesterKey(SemesterType semesterType, Year year) {
    return semesterType.name() + DELIMETER + year.toString();
  }

  @Override
  public void checkValidSemester(SemesterType semesterType, Year year) {
    if (semesterMap.containsKey(buildSemesterKey(semesterType, year))) {
      throw new InvalidSemesterKeyIdException(buildSemesterKey(semesterType, year));
    }
  }

  @Override
  public SemesterResult addNewSemesterResult(String studentId, SemesterResult semesterResult) throws SQLException {
    SemesterResult newSemesterResult = new SemesterResult(semesterResult);
    checkValidSemester(newSemesterResult.getSemesterType(), newSemesterResult.getYear());
    if (semesterResultRepository.getSemesterResultById(studentId, String.valueOf(semesterResult.getSemesterType()),
        semesterResult.getYear().getValue()) != null) {
      throw new AlreadyExistException("Exist sem-year!");
    } else {
      semesterResultRepository.insertSemesterResult(new SemesterResultEntity(studentId, newSemesterResult));
    }

    return newSemesterResult;

  }
}
