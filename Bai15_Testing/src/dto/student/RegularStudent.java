package dto.student;

import com.google.gson.Gson;
import dto.SemesterResult;
import enumeration.DepartmentType;
import java.time.Year;
import java.util.List;

public class RegularStudent extends BaseStudent {

  public RegularStudent(String id, String name, float entryScore, Year entryYear,
      DepartmentType departmentType, List<SemesterResult> semesterResults) {
    super(id, name, entryScore, entryYear, departmentType, semesterResults);
  }

  public RegularStudent(RegularStudent student) {
    super(student.getId(), student.getName(), student.getEntryScore(), student.getEntryYear(),
        student.getDepartmentType(), student.getSemesterResults());
  }

  public static RegularStudent createNewStudent(RegularStudent student) {
    student.setId(generateNewStudentId());
    return new RegularStudent(student);
  }

  @Override
  public String toString() {
    return this.getDepartmentType() + ": " +
        "Regular{" +
        "id='" + this.getId() + '\'' +
        ", name='" + this.getName() + '\'' +
        ", entryScore='" + this.getEntryScore() + '\'' +
        ", entryYear='" + this.getEntryYear() + '\'' +
        ", results='" + new Gson().toJson(this.getSemesterResults()) + '\'' +
        '}';
  }
}
