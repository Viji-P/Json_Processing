package com.sample.JsonUsingJackson;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsingJackson {
    
    public static void main(String[] args){

        File file=new File("src/data.json");

        ObjectMapper objectMapper=new ObjectMapper();

        try {
            JsonNode node =objectMapper.readTree(file);

            Map<String,Object> map=new LinkedHashMap<>();

            processJsonData(node,map,"");

            for(Map.Entry<String,Object> entry:map.entrySet()){
                System.out.println(entry.getKey()+" : "+entry.getValue());
            }


        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    private static void processJsonData(JsonNode node, Map<String, Object> map, String path) {

        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                processJsonData(field.getValue(), map, path + field.getKey());
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                processJsonData(node.get(i), map, path + "["+i+"]");
            }
        } else if (node.isValueNode()) {
            map.put(path, node.asText());
        }
    }
    
}
