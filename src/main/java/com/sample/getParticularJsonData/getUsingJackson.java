package com.sample.getParticularJsonData;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getUsingJackson {
    public static void main(String[] args) {
        
        ObjectMapper mapper=new ObjectMapper();
        try {
            JsonNode node=mapper.readTree(new File("src//DisposalInstructionStep.json"));

            Map<String,Object> dm=new HashMap<>();

            for(JsonNode node2:node.get("data")){

                for(JsonNode node3:node2.get("FG_TRD_PURPOSE_CODE")){

                    int amount=node3.get("AMT").asInt();

                    String identifier = node3.get("ID").asText(); 

                    dm.put(identifier,amount);
                }
            }
            for(Map.Entry<String,Object> entry:dm.entrySet()){
                System.out.println(entry.getValue());
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
}
