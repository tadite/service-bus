package edu.nc.servicebus.statistics.request;


import edu.nc.servicebus.statistics.Content;

public class RequestContent implements Content{

    private int time;
    private String endpoint;

    public RequestContent(int time, String endpoint){
        this.time = time;
        this.endpoint = endpoint;
    }

    @Override
    public int getTime() {
        return this.time;
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
