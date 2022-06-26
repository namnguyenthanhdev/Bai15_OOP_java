import builder.InServiceStudentBuilder;
import builder.RegularStudentBuilder;
import dto.InServicePlace;
import dto.SemesterResult;

import dto.student.InServiceStudent;

import enumeration.DepartmentType;
import enumeration.SemesterType;
import java.time.Year;
import service.inservicePlace.InServicePlaceImplement;
import service.inservicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceImplement;
import service.semester.SemesterServiceInterface;
import service.student.StudentManagerInterface;
import service.student.StudentManagerInterfaceBasicImplement;

import java.util.*;


public class Main {
  //Test 2;
  private final Set<DepartmentType> ALL_DEPARTMENTS = new HashSet<DepartmentType>() {{
    add(DepartmentType.BA);
    add(DepartmentType.IT);
    add(DepartmentType.BT);
  }};

  public static void main(String[] args) {

    InServicePlaceInterface inServicePlaceManager = new InServicePlaceImplement();
    SemesterServiceInterface semesterManager = new SemesterServiceImplement();
    StudentManagerInterface studentManager = new StudentManagerInterfaceBasicImplement(semesterManager);

    InServicePlace placeBinhDuong = inServicePlaceManager.createNewPlace(new InServicePlace("", "Bình Dương", "0908"));
    InServicePlace placeHCM = inServicePlaceManager.createNewPlace(new InServicePlace("", "HCM", "1900"));

    studentManager.addNewStudent(new RegularStudentBuilder()
        .setEntryScore(3)
        .setDepartmentType(DepartmentType.IT)
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
        .build()
    );
    studentManager.addNewStudent(new RegularStudentBuilder()
        .setEntryScore(5)
        .setDepartmentType(DepartmentType.BA)
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
        .build()
    );
    studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setEntryScore(9)
            .setDepartmentType(DepartmentType.BT)
            .setSemesterResults(Arrays.asList(
                new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
                new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
            .setInServicePlaceId(placeHCM.getPlaceId()).build());

    InServiceStudent studentBinhDuong = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setEntryScore(5)
            .setDepartmentType(DepartmentType.IT)
            .setSemesterResults(Arrays.asList(
                new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
                new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
            .setInServicePlaceId(placeBinhDuong.getPlaceId()).build());

    studentManager.printMapStudents();
    System.out.println("getAverageScoreOfStudentBySemester studentId=" + studentBinhDuong.getId() + " score="
        + studentManager.getAverageScoreOfStudentBySemester(
        studentBinhDuong.getId(), Year.of(2015), SemesterType.FIRST));

  }

}

