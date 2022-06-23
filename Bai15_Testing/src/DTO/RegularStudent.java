package DTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class RegularStudent extends Student{
    public RegularStudent(String id, double entryScore, List<Result> results) {
        super(id, entryScore, results);
    }



    @Override
    public String toString(){
        //Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Gson gson = new Gson();
        return "Regular{" +
                "id='" + this.getId() + '\'' +
                ", entryScore='" + this.getEntryScore() + '\'' +
                ", results={" + gson.toJson(this.getResults()) + '}' +
                '}';
    }
}
