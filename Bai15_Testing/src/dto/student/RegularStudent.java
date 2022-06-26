package dto.student;

import com.google.gson.Gson;

import dto.SemesterResult;
import java.util.List;

public class RegularStudent extends BaseStudent {

  public RegularStudent(String id, float entryScore, List<SemesterResult> results) {
    super(id, entryScore, results);
  }


  public static RegularStudent createNewStudent(RegularStudent dto) {
    return new RegularStudent(generateNewStudentId(), dto.getEntryScore(), dto.getSemesterResults());
  }

  public static RegularStudent createNewStudent(float entryScore, List<SemesterResult> semesterResults) {
    return new RegularStudent(generateNewStudentId(), entryScore, semesterResults);
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
