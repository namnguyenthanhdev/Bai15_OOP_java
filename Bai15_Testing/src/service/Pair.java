package service;


import java.util.Collections;
import java.util.Map;

public class Pair {
    public <T, U> Map<T, U> of (T first, U second){
        return Collections.singletonMap(first, second);
    }
 }
