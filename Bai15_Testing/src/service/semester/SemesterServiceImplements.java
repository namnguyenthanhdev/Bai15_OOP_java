package service.semester;

import enumeration.SemesterType;
import exception.InvalidSemesterKeyIdException;
import java.time.Year;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SemesterServiceImplements implements SemesterServiceInterface {

  private final String DELIMETER = "|";
  private Map<String, Set<Map<SemesterType, Year>>> semesterMap = new HashMap<>();

  public SemesterServiceImplements() {
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
}
