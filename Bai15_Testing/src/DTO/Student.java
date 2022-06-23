package DTO;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String id;
    private double entryScore;
    private List<Result> results = new ArrayList<>();

    public Student(String id, double entryScore, List<Result> results) {
        this.id = id;
        this.entryScore = entryScore;
        this.results = results;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getEntryScore() {
        return entryScore;
    }

    public void setEntryScore(double entryScore) {
        this.entryScore = entryScore;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
