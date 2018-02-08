package edu.nc.servicebus.model.security;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class Registration {

    private final String PATH = System.getProperty("user.dir") + "\\jsonUsers\\users.json";

    public void addUser(User user){
        try{
            addToJson(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addToJson(User user) throws Exception{
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(
                new FileReader(PATH));

        JSONObject obj = new JSONObject();
        obj.put("login", user.getUsername());
        obj.put("password", user.getPassword());

        array.add(obj);

        FileWriter jsonFile = new FileWriter(PATH);
        jsonFile.write(array.toJSONString());
        jsonFile.close();
    }
}
