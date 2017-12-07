package edu.nc.servicebus.model.response;

public class HttpResponse implements Response {

    private String data;

    public HttpResponse(String data){
        this.data=data;
    }

    @Override
    public String getRawData() {
        return data;
    }

    @Override
    public void setRawData(String data) {
        this.data=data;
    }
}
