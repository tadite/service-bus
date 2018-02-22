package edu.nc.servicebus.statistics.response;


import edu.nc.servicebus.statistics.Content;

public class ResponseContent implements Content{

    private int time;
    private String endpoint;
    private String content;

    public ResponseContent(int time, String endpoint, String content){
        this.time = time;
        this.endpoint = endpoint;
        this.content = content;
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
        return this.content;
    }
}
