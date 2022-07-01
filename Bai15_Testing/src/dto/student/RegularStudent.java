package dto.student;

import com.google.gson.Gson;
import dto.SemesterResult;
import enumeration.DepartmentType;
import java.time.Year;
import java.util.List;

public class RegularStudent extends BaseStudent {

  public RegularStudent(String id, float entryScore, List<SemesterResult> results, Year entryYear,
      DepartmentType departmentType) {
    super(id, entryScore, results, departmentType, entryYear);
  }


  public static RegularStudent createNewStudent(RegularStudent dto) {
    return new RegularStudent(generateNewStudentId(), dto.getEntryScore(), dto.getSemesterResults(), dto.getEntryYear(),
        dto.getDepartmentType());
  }

  @Override
  public String toString() {
    return "Regular{" +
        "id='" + this.getId() + '\'' +
        ", entryScore='" + this.getEntryScore() + '\'' +
        ", results={" + new Gson().toJson(this.getSemesterResults()) + '}' +
        '}';
  }
}
