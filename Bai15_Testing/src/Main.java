import DTO.Student;
import DTO.Result;
import DTO.RegularStudent;
import DTO.InServiceStudent;
import DTO.Department;

import Service.StudentManager;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class Main {

    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

//      TEST CASE 1:
        List<Result> results1 = new ArrayList<>();
        results1.add(new Result(Result.Semester.MID, 3.4));
        results1.add(new Result(Result.Semester.FINAL, 4.5));
        results1.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results2 = new ArrayList<>();
        results2.add(new Result(Result.Semester.MID, 0.4));
        results2.add(new Result(Result.Semester.FINAL, 4.5));
        results2.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results3 = new ArrayList<>();
        results3.add(new Result(Result.Semester.MID, 4.0));
        results3.add(new Result(Result.Semester.FINAL, 5.0));
        results3.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results4 = new ArrayList<>();
        results4.add(new Result(Result.Semester.MID, 3.4));
        results4.add(new Result(Result.Semester.FINAL, 4.5));
        results4.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results5 = new ArrayList<>();
        results5.add(new Result(Result.Semester.MID, 0.4));
        results5.add(new Result(Result.Semester.FINAL, 4.5));
        results5.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results6 = new ArrayList<>();
        results6.add(new Result(Result.Semester.MID, 4.0));
        results6.add(new Result(Result.Semester.FINAL, 5.0));
        results6.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results7 = new ArrayList<>();
        results7.add(new Result(Result.Semester.MID, 3.4));
        results7.add(new Result(Result.Semester.FINAL, 4.5));
        results7.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results8 = new ArrayList<>();
        results8.add(new Result(Result.Semester.MID, 0.4));
        results8.add(new Result(Result.Semester.FINAL, 4.5));
        results8.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Result> results9 = new ArrayList<>();
        results9.add(new Result(Result.Semester.MID, 4.0));
        results9.add(new Result(Result.Semester.FINAL, 5.0));
        results9.add(new Result(Result.Semester.SUMMER, 4.5));

        List<Student> students1 = new ArrayList<>();
        List<Student> students2 = new ArrayList<>();
        List<Student> students3 = new ArrayList<>();

        students1.add(new RegularStudent("ITRE01", 5.0, results1));
        students2.add(new RegularStudent("BTRE02", 7.0, results2));
        students3.add(new RegularStudent("BARE03", 8.0, results3));
        students1.add(new RegularStudent("ITRE04", 7.0, results4));
        students2.add(new InServiceStudent("BTSE01", 8.0, results5, "PTNK"));
        students2.add(new InServiceStudent("BTSE02", 7.0, results6, "LHP"));
        students3.add(new InServiceStudent("BASE03", 8.0, results7, "LHP"));
        students3.add(new InServiceStudent("BASE04", 7.0, results8, "TDN"));
        students1.add(new InServiceStudent("ITRE03", 8.0, results9, "TDN"));

        List<Department> departments = new ArrayList<>();
        departments.add(new Department(Department.DepartmentType.IT, students1));
        departments.add(new Department(Department.DepartmentType.BT, students2));
        departments.add(new Department(Department.DepartmentType.BA, students3));

//        studentManager.addStudentToList(student);
//        students.forEach(System.out::println);

//        studentManager.averageScoreBySemester("FINAL").forEach(System.out::println);

//        List<Student> students = Stream.of(students1, students2, students3).flatMap(Collection::stream).collect(Collectors.toList());




//        _______________Create a list of students from list of department
        List<Student> students = departments.stream().flatMap(t -> t.getStudents().stream()).collect(Collectors.toList());
        //students.forEach(System.out::println);



//        _______________Create a list of Result from list of student
        List<Result> results = students.stream().flatMap(t-> t.getResults().stream()).collect(Collectors.toList());
//        _________________Collect GPA by given semester
        results.stream().filter(c -> c.getSemester().equals(Result.Semester.valueOf("FINAL")))
                    .map(Result::getAverage).collect(Collectors.toList()).forEach(System.out::println);



//        List<RegularStudent> regularStudents = students.stream().filter(student -> student.getId().matches("^(IT|BA|BT)RE\\d{2}$"))
//                .map(student -> (RegularStudent) student).collect(Collectors.toList());



//        _______________Create a list of RegularStudent from list of student
        List<RegularStudent> regularStudents = students.stream().filter(student -> student instanceof RegularStudent)
                        .map(student -> (RegularStudent) student).collect(Collectors.toList());
//        regularStudents.forEach(System.out::println);


//        _________________Total regularStudents from all Department
        int regularStudentsSize = regularStudents.size();
        System.out.println("Sum of regular student: " + regularStudentsSize);

//        List<Student> highestEntryScoreOfStudentFromDepartment = departments.stream()
//                .filter(c -> c.getName().equals(Department.DepartmentType.valueOf("IT")))
//                .flatMap(t -> t.getStudents().stream()).collect(Collectors.toList());
//

        Map<Department.DepartmentType, List<Department>> departmentTypeDepartmentMap = departments.stream().collect(
        Collectors.groupingBy(Department::getName));
        List listOfValues = new ArrayList<>(departmentTypeDepartmentMap.values());


            Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
            (Optional<Employee> emp)-> emp.isPresent() ? emp.get().getName() : "none") );
    System.out.println("Max salaried employee's name: "+ maxSalaryEmp);
//
//

//        LinkedList employeeLinkedList = employeeList
//                .stream()
//                .collect(Collectors.toCollection(LinkedList::new));
//        System.out.println("No.of employees in employeeLinkedList: " + employeeLinkedList.size());
//        System.out.println("Employees in employeeLinkedList: " + employeeLinkedList);
//    }

        List listOfValues =

        System.out.println("Student with max entry score:"
                + (maxEntryScoreStu.isPresent()? maxEntryScoreStu.get():"Not Applicable"));

//        _______________Create a list of InServiceStudent from list of student
       List<InServiceStudent> inServiceStudents = students.stream().filter(student -> student instanceof InServiceStudent)
                .map(student -> (InServiceStudent) student).collect(Collectors.toList());

//        _______________ Display a map of Department with its id

//       departmentTypeListMap.forEach((k,v) -> {
//           Collectors.collectingAndThen(groupingBy(maxBy(Comparator.comparingDouble(Student::getEntryScore))));
//           System.out.println("key: " + k + "- value: " + v);
//               });
//       Map<Department.DepartmentType, List<InServiceStudent>> inServiceMap
//               = departmentTypeListMap



    }

}

/*
 Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String studentJson = gson.toJson(students);
        System.out.println(studentJson);
        System.out.println();

        String result1Json = gson.toJson(results1);
        System.out.println(result1Json);
        System.out.println();

        System.out.println(gson.toJson(results2));
        System.out.println();

        System.out.println(gson.toJson(results3));
        System.out.println();


        Type studentListType = new TypeToken<ArrayList<RegularStudent>>(){}.getType();
        List<RegularStudent> studentList = gson.fromJson(studentJson, studentListType);
        studentList.forEach(System.out::println);

 */
