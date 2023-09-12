package com.sample;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

class Address {
    private String street;
    private String city;
    private String state;
    private String postal_code;

    @Override
    public String toString() {
        return street + ", " + city + ", " + state + " " + postal_code;
    }
}



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
                        Address address = gson.fromJson(value, Address.class);
                        userData.put(key, address);
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

