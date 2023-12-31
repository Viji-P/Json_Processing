package com.sample.getParticularJsonData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetJsonData{
    public static void main(String[] args) {

        
        File filepath=new File("src//DisposalInstructionStep.json");

        Map<String,Object> jsonMap =convertToMap(filepath);

        if(jsonMap != null){
           getParticularJsonData(jsonMap);
        }
    }

    

    private static Map<String, Object> convertToMap(File filepath) {

        ObjectMapper mapper =new ObjectMapper();

        try {
                return mapper.readValue(filepath, Map.class);
            } 
            catch (IOException e) {
                
                e.printStackTrace();
            }
        
        return null;
    }
    
    private static void getParticularJsonData(Map<String, Object> jsonMap) {

        try {
            List<Map<String,Object>>dMap=(List<Map<String, Object>>) jsonMap.get("data");

            for(Map<String,Object> dataNode:dMap){

                List<Map<String,Object>> purposeCodeList=(List<Map<String, Object>>) dataNode.get("FG_TRD_PURPOSE_CODE");

                for(Map<String,Object> purposeCode :purposeCodeList){
                    Object amt=purposeCode.get("AMT");

                    System.out.println(amt);

                }
                }
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }

}