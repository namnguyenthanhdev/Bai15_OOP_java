import builder.InServiceStudentBuilder;
import builder.RegularStudentBuilder;
import dto.InServicePlace;
import dto.SemesterResult;
import dto.student.InServiceStudent;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import service.inServicePlace.InServicePlaceImplements;
import service.inServicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceImplements;
import service.semester.SemesterServiceInterface;
import service.student.StudentManagerImplements;
import service.student.StudentManagerInterface;

public class main {

  private final Set<DepartmentType> ALL_DEPARTMENT = new HashSet<DepartmentType>() {{
    add(DepartmentType.BA);
    add(DepartmentType.BT);
    add(DepartmentType.IT);
  }};

  public static void main(String[] args) {
    InServicePlaceInterface inServicePlaceManager = new InServicePlaceImplements();
    SemesterServiceInterface semesterManager = new SemesterServiceImplements();
    StudentManagerInterface studentManager = new StudentManagerImplements(semesterManager, inServicePlaceManager);

    InServicePlace placeBinhDuong = inServicePlaceManager.createNewPlace(
        new InServicePlace("", "Binh Duong", "0938948764"));
    InServicePlace placeHCM = inServicePlaceManager.createNewPlace(new InServicePlace("", "TP.HCM", "0902297861"));

    studentManager.addNewStudent(new RegularStudentBuilder()
        .setName("Le Van Dat")
        .setEntryScore(3)
        .setDepartmentType(DepartmentType.IT)
        .setEntryYear(Year.of(2011))
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 3),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 4)))
        .build()
    );
    studentManager.addNewStudent(new RegularStudentBuilder()
        .setName("Le Ba Kha")
        .setEntryScore(5)
        .setDepartmentType(DepartmentType.IT)
        .setEntryYear(Year.of(2014))
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 5),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
        .build()
    );
    studentManager.addNewStudent(new InServiceStudentBuilder()
        .setName("Le Bong")
        .setEntryScore(9)
        .setDepartmentType(DepartmentType.IT)
        .setEntryYear(Year.of(2012))
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 9)))
        .setInServicePlaceId(placeHCM.getId()).build());

    InServiceStudent studentBinhDuong = (InServiceStudent) studentManager.addNewStudent(new InServiceStudentBuilder()
        .setName("Le Be Let")
        .setEntryScore(5)
        .setDepartmentType(DepartmentType.IT)
        .setEntryYear(Year.of(2013))
        .setSemesterResults(Arrays.asList(
            new SemesterResult(SemesterType.FIRST, Year.of(2015), 8),
            new SemesterResult(SemesterType.SECOND, Year.of(2015), 10)))
        .setInServicePlaceId(placeBinhDuong.getId()).build());

    System.out.println("getAverageScoreOfStudentBySemester studentId=" + studentBinhDuong.getId() + " score="
        + studentManager.getAverageScoreOfStudentBySemester(
        studentBinhDuong.getId(), Year.of(2015), SemesterType.FIRST));

    System.out.println(studentBinhDuong.getId() + " is regular student? -> " + studentManager.isRegularStudent(
        studentBinhDuong.getId()));

    System.out.println("\nTotal regular student by department: " + studentManager.getTotalRegularStudentByDepartment(
        DepartmentType.IT));

    System.out.println("\nIn service student list by department and place: ");

    studentManager.getInServiceStudentListByDepartmentTypeAndInServicePlace(DepartmentType.IT, "Binh Duong")
        .forEach(System.out::println);

    System.out.println("\nAverage score larger than 8 in recent semester: ");
    studentManager.getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType.IT)
        .forEach(System.out::println);

    System.out.println("\nMax score student from all semester: ");
    studentManager.getMaxAverageScoreStudentFromAllSemester(DepartmentType.IT)
        .forEach(System.out::println);

    System.out.println("\nSort student by ascending score and descending year: ");
    studentManager.sortStudentAscendingByScoreAndDescendingByYear(DepartmentType.IT)
        .forEach(System.out::println);

  }

}
