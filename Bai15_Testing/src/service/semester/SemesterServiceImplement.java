package service.semester;

import enumeration.SemesterType;
import java.security.InvalidParameterException;
import java.time.Year;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SemesterServiceImplement implements SemesterServiceInterface {
  private Set<Map<SemesterType, Year>> pairSemester = new HashSet<>();

  private Map<String, Set<Map<SemesterType, Year>>> semesterMap = new HashMap<>();

  private final String DELIMITER = "|";

  public SemesterServiceImplement() {
    createSemester(SemesterType.FIRST, Year.of(2015));
    createSemester(SemesterType.SECOND, Year.of(2015));
    createSemester(SemesterType.FIRST, Year.of(2016));
    createSemester(SemesterType.SECOND, Year.of(2016));
    createSemester(SemesterType.FIRST, Year.of(2017));
    createSemester(SemesterType.SECOND, Year.of(2017));
    createSemester(SemesterType.FIRST, Year.of(2018));
    createSemester(SemesterType.SECOND, Year.of(2018));
  }



  private Set<Map<SemesterType, Year>> createSemester(SemesterType semesterType, Year year) {
    Set<Map<SemesterType, Year>> newData = new HashSet<> ();
    semesterMap.put(buildSemesterKey(semesterType, year), newData);
    return newData;
  }

  private String buildSemesterKey(SemesterType semesterType, Year year) {
    return semesterType.name() + DELIMITER + year.toString();
  }

  @Override
  public void checkValidSemester(SemesterType semesterType, Year year) {
    if (!semesterMap.containsKey(buildSemesterKey(semesterType, year))) {
      throw new InvalidParameterException();
    }
  }
}
