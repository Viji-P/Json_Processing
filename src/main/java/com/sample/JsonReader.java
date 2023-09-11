package com.sample;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class JsonUsingGson{

    private String name;
    private int age;
    private String email;

    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

 public String toString(){
        return "Name :"+name+" Age: "+age+" Email: "+email;
    }


}

public class JsonReader {

    public static void main(String[] args) {

        try(FileReader reader =new FileReader("src/data.json")) {
            Gson gson =new Gson();
            List<Map<String,Object>> listOfMap=new ArrayList<>();

            List<JsonUsingGson> usingJson = gson.fromJson(reader, new TypeToken<List<JsonUsingGson>>(){}.getType());

            for(JsonUsingGson data:usingJson){
                Map<String,Object> map=new HashMap<>();

                map.put("name", data.getName());
                map.put("age", data.getAge());
                map.put("email",data.getEmail());

                listOfMap.add(map);
            }

            System.out.println(listOfMap);
        }
        catch(Exception e){
            e.getStackTrace();
        }
        
    }
}

