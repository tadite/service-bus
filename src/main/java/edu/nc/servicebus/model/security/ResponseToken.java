package edu.nc.servicebus.model.security;


public class ResponseToken {

    private String token;

    public ResponseToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString(){
        return String.format("{\"token\":\"}", this.token);
    }
}
