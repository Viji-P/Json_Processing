package com.sample.ReadingJson;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




public class JsonReader {

    public static void main(String[] args) {

        try(FileReader reader =new FileReader("src/data.json")) {
            Gson gson =new Gson();
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);

            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                //System.out.println(jsonObject);

                Map<String, Object> userData = new HashMap<>();

                for (String key : jsonObject.keySet()) {
                    JsonElement value = jsonObject.get(key);
                    if (value.isJsonPrimitive()) {
                        userData.put(key, value.getAsString());
                    } else if (value.isJsonObject()) {
                        JsonObject addObject =value.getAsJsonObject();
                        Map<String,Object> addMap=new HashMap<>();
                        for(String addKey:addObject.keySet()){
                            addMap.put(addKey, addObject.get(addKey).getAsString());
                        }
                        userData.put(key, addMap);
                    } else if (value.isJsonArray()) {
                        JsonArray jsonArrayValue = value.getAsJsonArray();
                        if (jsonArrayValue.size() > 0 && jsonArrayValue.get(0).isJsonPrimitive()) {
                            String[] stringArray = new String[jsonArrayValue.size()];
                            for (int i = 0; i < jsonArrayValue.size(); i++) {
                                stringArray[i] = jsonArrayValue.get(i).getAsString();
                            }
                            userData.put(key, Arrays.toString(stringArray));
                        }
                    }
                }

                System.out.println(userData);
            }
            

        }
        catch(Exception e){
            e.getStackTrace();
        }
        
    }
}

