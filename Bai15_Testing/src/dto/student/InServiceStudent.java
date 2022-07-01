package dto.student;

import com.google.gson.Gson;
import dto.SemesterResult;
import enumeration.DepartmentType;
import java.time.Year;
import java.util.List;

public class InServiceStudent extends BaseStudent {

  private String inServicePlaceId;

  //TODO: use as constructor in main()
  public InServiceStudent(String id, String name, float entryScore, Year entryYear,
      DepartmentType departmentType, List<SemesterResult> semesterResults,
      String inServicePlaceId) {
    super(id, name, entryScore, entryYear, departmentType, semesterResults);
    this.inServicePlaceId = inServicePlaceId;
  }

  //TODO: create in-service student with auto-generator id
  public InServiceStudent(InServiceStudent student) {
    super(student.getId(), student.getName(), student.getEntryScore(), student.getEntryYear(),
        student.getDepartmentType(), student.getSemesterResults());
    setInServicePlaceId(student.getInServicePlaceId());
  }

  public static InServiceStudent createNewStudent(InServiceStudent student) {
    student.setId(generateNewStudentId());
    return new InServiceStudent(student);
  }

  public String getInServicePlaceId() {
    return inServicePlaceId;
  }

  public void setInServicePlaceId(String inServicePlaceId) {
    this.inServicePlaceId = inServicePlaceId;
  }

  @Override
  public String toString() {
    return this.getDepartmentType() + ": " +
        "InService{" +
        "id='" + this.getId() + '\'' +
        ", name='" + this.getName() + '\'' +
        ", entryScore='" + this.getEntryScore() + '\'' +
        ", entryYear='" + this.getEntryYear() + '\'' +
        ", results='" + new Gson().toJson(this.getSemesterResults()) + '\'' +
        ", place=" + this.inServicePlaceId +
        '}';
  }
}
