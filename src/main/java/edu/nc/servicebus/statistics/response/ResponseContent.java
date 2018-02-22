package edu.nc.servicebus.statistics.response;


import edu.nc.servicebus.statistics.Content;

public class ResponseContent implements Content{

    private String endpoint;
    private String content;

    public ResponseContent(String endpoint, String content){
        this.endpoint = endpoint;
        this.content = content;
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
