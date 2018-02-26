package edu.nc.servicebus.statistics.categories;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.stereotype.Component;

import java.io.FileReader;

@Component
public class JsonCategories {

    private final String PATH = System.getProperty("user.dir") + "/json/categories/categories.json";

    private JSONParser parser;
    private JSONObject type;

    {
        parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        try{
            type = (JSONObject) parser.parse(new FileReader(PATH));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkEndpoint(String category, String name){
        JSONArray array = (JSONArray) type.get(category);
        for (int i = 0; i < array.size(); i++){
            JSONObject obj = (JSONObject) array.get(i);
            if (name.contains(obj.get("name").toString())){
                return true;
            }
        }
        return false;
    }
}
