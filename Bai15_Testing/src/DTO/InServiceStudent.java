package DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class InServiceStudent extends Student{
    private String education;

    public InServiceStudent(String id, double entryScore, List<Result> results, String education) {
        super(id, entryScore, results);
        this.education = education;
    }



    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        return "InService{" +
                "id='" + this.getId() + '\'' +
                ", entryScore=" + this.getEntryScore() +
                ", results=" + gson.toJson(this.getResults()) +
                ", education=" + this.getEducation() +
                '}';
    }
}
