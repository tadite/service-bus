package edu.nc.servicebus.model.security.jwt;


import java.io.Serializable;

public class TokenResponse implements Serializable{

    private String token;

    public TokenResponse(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return String.format("{\"token\":\"%s\"}", this.token);
    }
}
