package service.semester;

import enumeration.SemesterType;
import java.time.Year;
//import javafx.util.Pair;


public interface SemesterServiceInterface {

  void checkValidSemester(SemesterType semesterType, Year year);


}
