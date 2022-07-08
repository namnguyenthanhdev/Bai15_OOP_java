import builder.InServiceStudentBuilder;
import dto.InServicePlace;
import dto.SemesterResult;
import dto.student.InServiceStudent;
import entity.InServicePlaceEntity;
import enumeration.DepartmentType;
import enumeration.SemesterType;
import enumeration.StudentType;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import repository.inServicePlace.InServicePlaceRepository;
import repository.semester.SemesterResultRepository;
import repository.student.StudentRepository;
import service.inServicePlace.InServicePlaceAdvanceImpl;
import service.inServicePlace.InServicePlaceAdvanceInterface;
import service.inServicePlace.InServicePlaceImplements;
import service.inServicePlace.InServicePlaceInterface;
import service.semester.SemesterServiceAdvanceImplements;
import service.semester.SemesterServiceAdvanceInterface;
import service.semester.SemesterServiceImplements;
import service.semester.SemesterServiceInterface;
import service.student.StudentManagerAdvanceImpl;
import service.student.StudentManagerAdvanceInterface;

public class main {

  private final Set<DepartmentType> ALL_DEPARTMENT = new HashSet<DepartmentType>() {{
    add(DepartmentType.BA);
    add(DepartmentType.BT);
    add(DepartmentType.IT);
  }};

  public static void main(String[] args) throws SQLException {

//    InServicePlaceInterface inServicePlaceManager = new InServicePlaceImplements();
    StudentRepository studentRepository = new StudentRepository();
    SemesterResultRepository semesterResultRepository = new SemesterResultRepository();
    InServicePlaceRepository inServicePlaceRepository = new InServicePlaceRepository();
//    List<InServiceStudent> students = new ArrayList<>()

    InServicePlaceAdvanceInterface inServicePlaceAdvanceManager = new InServicePlaceAdvanceImpl(inServicePlaceRepository);
    SemesterServiceAdvanceInterface semesterManager = new SemesterServiceAdvanceImplements(semesterResultRepository);
    StudentManagerAdvanceInterface studentManager = new StudentManagerAdvanceImpl(semesterManager, inServicePlaceAdvanceManager, studentRepository);

    InServicePlace placeBinhDuong = inServicePlaceAdvanceManager.createNewPlace(
        new InServicePlace("", "Binh Duong", "0938948764"));
    InServicePlace placeHCM = inServicePlaceAdvanceManager.createNewPlace(
        new InServicePlace("", "HCM", "0902297861"));


//    InServicePlace placeBinhDuong = inServicePlaceManager.createNewPlace(
//        new InServicePlace("", "Binh Duong", "0938948764"));
//    InServicePlace placeHCM = inServicePlaceManager.createNewPlace(
//        new InServicePlace("", "TP.HCM", "0902297861"));


    System.out.println("\n ___________________ studentHCM1: ");
        InServiceStudent studentHCM1 = (InServiceStudent) studentManager.addNewStudent(new InServiceStudentBuilder()
        .setName("Le Bong")
        .setEntryScore(9)
        .setDepartmentType(DepartmentType.IT)
        .setEntryYear(Year.of(2010))
        .setInServicePlaceId(placeHCM.getId()).build());
    System.out.println("\n ___________________ Result of studentHCM1: ");
    semesterManager.addNewSemesterResult(studentHCM1.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2011), 10));
    semesterManager.addNewSemesterResult(studentHCM1.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2011), 9));



    System.out.println("\n ___________________ studentBinhDuong2: ");
    InServiceStudent studentBinhDuong2 = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setName("Le Be Let")
            .setEntryScore(5)
            .setDepartmentType(DepartmentType.IT)
            .setEntryYear(Year.of(2013))
            .setInServicePlaceId(placeBinhDuong.getId()).build());
    System.out.println("\n ___________________ Result of studentBinhDuong2: ");
    semesterManager.addNewSemesterResult(studentBinhDuong2.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2013), 1));
    semesterManager.addNewSemesterResult(studentBinhDuong2.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2013), 2));
    semesterManager.addNewSemesterResult(studentBinhDuong2.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2014), 5));
    semesterManager.addNewSemesterResult(studentBinhDuong2.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2014), 9));



    System.out.println("\n ___________________ studentBinhDuong3: ");
    InServiceStudent studentBinhDuong3 = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setName("Le Hoang Nam")
            .setEntryScore(9)
            .setDepartmentType(DepartmentType.IT)
            .setEntryYear(Year.of(2013))
            .setInServicePlaceId(placeBinhDuong.getId()).build());
    System.out.println("\n ___________________ Result of studentBinhDuong3: ");
    semesterManager.addNewSemesterResult(studentBinhDuong3.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2012), 10));
    semesterManager.addNewSemesterResult(studentBinhDuong3.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2012), 10));
    semesterManager.addNewSemesterResult(studentBinhDuong3.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2013), 8));
    semesterManager.addNewSemesterResult(studentBinhDuong3.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2013), 9));



    System.out.println("\n ___________________ studentBinhDuong4: ");
    InServiceStudent studentBinhDuong4 = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setName("Le Be La")
            .setEntryScore(8)
            .setDepartmentType(DepartmentType.IT)
            .setEntryYear(Year.of(2012))
            .setInServicePlaceId(placeBinhDuong.getId()).build());
    System.out.println("\n ___________________ Result of studentBinhDuong4: ");
    semesterManager.addNewSemesterResult(studentBinhDuong4.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2011), 5));
    semesterManager.addNewSemesterResult(studentBinhDuong4.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2011), 8));
    semesterManager.addNewSemesterResult(studentBinhDuong4.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2012), 4));
    semesterManager.addNewSemesterResult(studentBinhDuong4.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2012), 8));


    System.out.println("\n ___________________ studentBinhDuong5: ");
    InServiceStudent studentBinhDuong5 = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setName("Le Thuy")
            .setEntryScore(8)
            .setDepartmentType(DepartmentType.IT)
            .setEntryYear(Year.of(2012))
            .setInServicePlaceId(placeBinhDuong.getId()).build());
    System.out.println("\n ___________________ Result of studentBinhDuong5: ");
    semesterManager.addNewSemesterResult(studentBinhDuong5.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2011), 8));
    semesterManager.addNewSemesterResult(studentBinhDuong5.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2011), 9));
    semesterManager.addNewSemesterResult(studentBinhDuong5.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2013), 8));
    semesterManager.addNewSemesterResult(studentBinhDuong5.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2013), 6));



    System.out.println("\n ___________________ studentBinhDuong1: ");
    InServiceStudent studentBinhDuong1 = (InServiceStudent) studentManager.addNewStudent(
        new InServiceStudentBuilder()
            .setName("Le Le")
            .setEntryScore(8)
            .setDepartmentType(DepartmentType.BA)
            .setEntryYear(Year.of(2010))
            .setInServicePlaceId(placeBinhDuong.getId()).build());
    System.out.println("\n ___________________ Result of studentBinhDuong1: ");
    semesterManager.addNewSemesterResult(studentBinhDuong1.getId(), new SemesterResult(SemesterType.FIRST, Year.of(2014), 8));
    semesterManager.addNewSemesterResult(studentBinhDuong1.getId(), new SemesterResult(SemesterType.SECOND, Year.of(2014), 10));

    System.out.println("\n ___________________ AVG score of studentBinhDuong1: ");
    System.out.println(
        "getAverageScoreOfStudentBySemester studentId=" + studentBinhDuong1.getId() + " score="
            +     studentManager.getAverageScoreOfStudentBySemester(studentBinhDuong1.getId(), Year.of(2014), SemesterType.FIRST));

    System.out.println("\n ___________________ In service student list grouping by address and department: ");
    studentManager.getInServiceStudentListByDepartmentTypeAndInServicePlace(
        StudentType.IN_SERVICE, DepartmentType.IT, placeBinhDuong.getAddress()).forEach(System.out::println);

    System.out.println("\n ___________________ getAverageScoreHigherThanEightInRecentSemesterByDepartmentType: ");
    studentManager.getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType.IT).forEach(System.out::println);


    System.out.println("\n ___________________ getMaxAverageScoreByDepartmentType: ");
    studentManager.getMaxAverageScoreStudentFromAllSemester(DepartmentType.IT).forEach(System.out::println);

    System.out.println("\n ___________________ sortStudentAscendingByScoreAndDescendingByYear: ");
    studentManager.sortStudentAscendingByScoreAndDescendingByYear(DepartmentType.IT).forEach(System.out::println);

//    studentManager.addNewStudent(new RegularStudentBuilder()
//        .setName("Le Van Dat")
//        .setEntryScore(3)
//        .setDepartmentType(DepartmentType.IT)
//        .setEntryYear(Year.of(2011))
//        .setSemesterResults(Arrays.asList(
//            new SemesterResult(SemesterType.FIRST, Year.of(2015), 3),
//            new SemesterResult(SemesterType.SECOND, Year.of(2015), 4)))
//        .build()
//    );
//    studentManager.addNewStudent(new RegularStudentBuilder()
//        .setName("Le Ba Kha")
//        .setEntryScore(5)
//        .setDepartmentType(DepartmentType.IT)
//        .setEntryYear(Year.of(2014))
//        .setSemesterResults(Arrays.asList(
//            new SemesterResult(SemesterType.FIRST, Year.of(2015), 5),
//            new SemesterResult(SemesterType.SECOND, Year.of(2015), 8)))
//        .build()
//    );
//    studentManager.addNewStudent(new InServiceStudentBuilder()
//        .setName("Le Bong")
//        .setEntryScore(9)
//        .setDepartmentType(DepartmentType.IT)
//        .setEntryYear(Year.of(2012))
//        .setSemesterResults(Arrays.asList(
//            new SemesterResult(SemesterType.FIRST, Year.of(2015), 10),
//            new SemesterResult(SemesterType.SECOND, Year.of(2015), 9)))
//        .setInServicePlaceId(placeHCM.getId()).build());
//
//    InServiceStudent studentBinhDuong = (InServiceStudent) studentManager.addNewStudent(
//        new InServiceStudentBuilder()
//            .setName("Le Be Let")
//            .setEntryScore(5)
//            .setDepartmentType(DepartmentType.IT)
//            .setEntryYear(Year.of(2013))
//            .setSemesterResults(Arrays.asList(
//                new SemesterResult(SemesterType.FIRST, Year.of(2015), 8),
//                new SemesterResult(SemesterType.SECOND, Year.of(2015), 10)))
//            .setInServicePlaceId(placeBinhDuong.getId()).build());
//
//    System.out.println(
//        "getAverageScoreOfStudentBySemester studentId=" + studentBinhDuong.getId() + " score="
//            + studentManager.getAverageScoreOfStudentBySemester(
//            studentBinhDuong.getId(), Year.of(2015), SemesterType.FIRST));
//
//    System.out.println(
//        studentBinhDuong.getId() + " is regular student? -> " + studentManager.isRegularStudent(
//            studentBinhDuong.getId()));
//
//    System.out.println("\nTotal regular student by department: "
//        + studentManager.getTotalRegularStudentByDepartment(DepartmentType.IT));
//
//    System.out.println("\nIn service student list by department and place: ");
//
//    studentManager.getInServiceStudentListByDepartmentTypeAndInServicePlace(DepartmentType.IT,
//            "Binh Duong")
//        .forEach(System.out::println);
//
//    System.out.println("\nAverage score larger than 8 in recent semester: ");
//    studentManager.getAverageScoreHigherThanEightInRecentSemesterByDepartmentType(DepartmentType.IT)
//        .forEach(System.out::println);
//
//    System.out.println("\nMax score student from all semester: ");
//    studentManager.getMaxAverageScoreStudentFromAllSemester(DepartmentType.IT)
//        .forEach(System.out::println);
//
//    System.out.println("\nSort student by ascending score and descending year: ");
//    studentManager.sortStudentAscendingByScoreAndDescendingByYear(DepartmentType.IT)
//        .forEach(System.out::println);

  }

}
