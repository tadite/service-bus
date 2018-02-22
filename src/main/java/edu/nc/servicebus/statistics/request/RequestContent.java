package edu.nc.servicebus.statistics.request;


import edu.nc.servicebus.statistics.Content;

public class RequestContent implements Content{

    private String endpoint;

    public RequestContent(String endpoint){
        this.endpoint = endpoint;
    }

    @Override
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override
    public String getContent() {
        return null;
    }
}
