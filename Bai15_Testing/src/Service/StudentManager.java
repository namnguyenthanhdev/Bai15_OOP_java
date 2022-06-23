package Service;

import DTO.Result;
import DTO.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StudentManager {
    List<Student> students = new ArrayList<>();
    List<Result> results = students.stream().flatMap(t-> t.getResults().stream()).collect(Collectors.toList());

    public List<Double> averageScoreBySemester(String requiredSemester){
        return results.stream().filter(c -> c.getSemester().equals(Result.Semester.valueOf(requiredSemester)))
                .map(Result::getAverage).collect(Collectors.toList());
//        return results.stream().collect(Collectors.groupingBy(Result::getSemester)).

    }


    public void displayStudent(List<Student> students){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonStudent = gson.toJson(students);
        System.out.println(jsonStudent);
        //Map<String, List<Person>> peopleByCity      = personStream.collect(Collectors.groupingBy(Person::getCity));
//        Map<String, Map<String, List<Person>>> peopleByStateAndCity
//                = personStream.collect(Collectors.groupingBy(Person::getState,
//                Collectors.groupingBy(Person::getCity)));

    }
}
