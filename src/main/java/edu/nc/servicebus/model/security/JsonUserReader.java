package edu.nc.servicebus.model.security;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class JsonUserReader {

    private final String PATH = System.getProperty("user.dir") + "\\jsonUsers\\users.json";

    private List<User> users;

    private void getUserDB() {

        try {
            String fullPath = new String(Files.readAllBytes(Paths.get(PATH)));
            ObjectMapper obj = new ObjectMapper();
            users = obj.readValue(fullPath, new TypeReference<List<User>>(){});
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public User getUsersByLogin(String login){
        getUserDB();
        for (User user : users){
            if (login.equals(user.getUsername())){
                return user;
            }
        }
        return null;
    }
}
