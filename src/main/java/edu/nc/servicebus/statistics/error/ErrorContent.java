package edu.nc.servicebus.statistics.error;


import edu.nc.servicebus.statistics.Content;

public class ErrorContent implements Content{

    private String endpoint;
    private String content;

    public ErrorContent(String endpoint, String content){
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
