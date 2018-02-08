package edu.nc.servicebus.model.parser;

import org.springframework.stereotype.Component;

@Component
public class JsonPath {

    public String getActionPath(){
        return "/json/";
    }

    public String getExtension(){
        return ".json";
    }
}
