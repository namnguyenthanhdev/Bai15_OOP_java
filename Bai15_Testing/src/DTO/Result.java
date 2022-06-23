package DTO;

import java.util.function.Predicate;

public class Result {
    public enum Semester {MID, FINAL, SUMMER};
    private Semester semester;
    private double average;

    public Result(Semester semester, double average) {
            this.semester = semester;
            this.average = average;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }


    @Override
    public String toString() {
        return "Result{" +
                "semester=" + semester +
                ", average=" + average +
                '}';
    }
}
