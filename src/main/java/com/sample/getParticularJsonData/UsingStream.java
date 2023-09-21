package com.sample.getParticularJsonData;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UsingStream {
    public static void main(String[] args) {
        
        File filePath=new File("src//DisposalInstructionStep.json");

        Map<String,Object> jsonMap=convertToMap(filePath);

        if(jsonMap !=null){
            getJsonData(jsonMap);
        }
    }

    

    private static Map<String, Object> convertToMap(File filePath) {

        ObjectMapper mapper=new ObjectMapper();

        try {
            return mapper.readValue(filePath,Map.class);

        }  catch (IOException e) {
            
            e.printStackTrace();
        }
        return null;
    }
    private static void getJsonData(Map<String, Object> jsonMap) {

        try {
            List<Map<String, Object>> dMap = (List<Map<String, Object>>) jsonMap.get("data");

            List<Object> collectedList =  dMap.stream()
                .map(dataNode -> (List<Map<String, Object>>) dataNode.get("FG_TRD_PURPOSE_CODE"))
                .flatMap(List::stream)
                .map(purposeCode -> purposeCode.get("AMT"))
                .collect(Collectors.toList());

                System.out.println(collectedList);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    
}
