package service.semester;

import enumeration.SemesterType;
import java.time.Year;

public interface SemesterServiceInterface {

  void checkValidSemester(SemesterType semesterType, Year year);

}
