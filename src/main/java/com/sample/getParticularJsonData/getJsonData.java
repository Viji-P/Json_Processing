package com.sample.getParticularJsonData;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class getJsonData {
    public static void main(String[] args) {
        try {
            String jsonString=new String(Files.readAllBytes(Paths.get("src//DisposalInstructionStep.json")));

            JSONObject jsonObject=new JSONObject(jsonString);

            JSONArray jArray=jsonObject.getJSONArray("data");

            int index=0;

            JSONObject jsonObject2=jArray.getJSONObject(index);

            JSONArray jArray2=jsonObject2.optJSONArray("FG_TRD_PURPOSE_CODE");

            for(int i=0;i<jArray2.length();i++){

                JSONObject jsonObject3=jArray2.getJSONObject(i);

                int amount=jsonObject3.getInt("AMT");

                System.out.println(amount);

            }

            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
