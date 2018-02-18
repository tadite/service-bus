package edu.nc.servicebus.statistics;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Statistic {

    private final String PATH = System.getProperty("user.dir") + "\\json\\categories\\categories.json";

    private List<RequestData> requestDataList;
    private JSONParser parser;
    private JSONObject type;

    {
        requestDataList = new ArrayList<>();
        parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        try{
            type = (JSONObject) parser.parse(new FileReader(PATH));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void add(long time, List<String> contents){
        int beach = 0;
        int sport = 0;
        int excursion = 0;
        for (String endpoint: contents){
            if (checkEndpoint("beach", endpoint)){
                beach++;
            }
            if (checkEndpoint("sport", endpoint)){
                sport++;
            }
            if (checkEndpoint("excursion", endpoint)){
                excursion++;
            }
        }
        requestDataList.add(new RequestData(time, contents.size(), beach, sport, excursion));
    }

    public List<RequestData> getRequestData(){
        return requestDataList;
    }

    private boolean checkEndpoint(String category, String name){
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
