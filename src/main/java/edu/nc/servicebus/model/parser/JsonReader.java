package edu.nc.servicebus.model.parser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class JsonReader {

    @Autowired
    private JsonPath jsonPath;
    {
        jsonPath = new JsonPath();
    }

    public String getJsonAction(String name) throws IOException{
        return new String(Files.readAllBytes(Paths.get(getPathToJson(name))));
    }

    private String getPathToJson(String name){
        String mainDir = System.getProperty("user.dir");

        StringBuilder fullPath = new StringBuilder();

        fullPath.append(mainDir);
        fullPath.append(jsonPath.getActionPath());
        fullPath.append(name);
        fullPath.append(jsonPath.getExtension());

        return fullPath.toString();
    }
}
