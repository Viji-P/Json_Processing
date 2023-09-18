package com.sample.getParticularJsonData;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetJsonData{
    public static void main(String[] args) {

        ObjectMapper mapper =new ObjectMapper();
        
        File filepath=new File("src//DisposalInstructionStep.json");

        Map<String,Object> jsonMap =convertToMap(filepath,mapper);

        if(jsonMap != null){
           getParticularJsonData(jsonMap,mapper);
        }
    }

    

    private static Map<String, Object> convertToMap(File filepath, ObjectMapper mapper) {

        try {
                return mapper.readValue(filepath, Map.class);
            } 
            catch (IOException e) {
                
                e.printStackTrace();
            }
        
        return null;
    }
    
    private static void getParticularJsonData(Map<String, Object> jsonMap, ObjectMapper mapper) {

        try {
            JsonNode rootNode=mapper.convertValue(jsonMap, JsonNode.class);

            JsonNode dataNode = rootNode.get("data");
                for (JsonNode node : dataNode) {
                        for (JsonNode purposeCode : node.get("FG_TRD_PURPOSE_CODE")) {
                            double amt= purposeCode.get("AMT").asDouble();
    
                            System.out.println("Amount: " + amt);
                        }
                    }
               
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    }

}