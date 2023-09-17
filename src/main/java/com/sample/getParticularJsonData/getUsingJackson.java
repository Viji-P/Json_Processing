package com.sample.getParticularJsonData;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class getUsingJackson {
    public static void main(String[] args) {
        
        ObjectMapper mapper=new ObjectMapper();
        try {
            JsonNode node=mapper.readTree(new File("src//DisposalInstructionStep.json"));

            for(JsonNode node2:node.get("data")){

                for(JsonNode node3:node2.get("FG_TRD_PURPOSE_CODE")){

                    System.out.println(node3.get("AMT").asInt());
                }
            }
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
    
}
