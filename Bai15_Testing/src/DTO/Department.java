package DTO;

import com.google.gson.Gson;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode
public class Department {
    public enum DepartmentType{
        IT,
        BT,
        BA
    }
    private DepartmentType name;
    private List<Student> students;

    public Department() {
    }

    public Department(DepartmentType name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public DepartmentType getName() {
        return name;
    }

    public void setName(DepartmentType name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return "Department{" +
                "name=" + name +
                ", students=" + gson.toJson(students) +
                '}';
    }
}
