package edu.nc.servicebus.model.response;


public class SoapResponse implements Response{

    private String data;

    public SoapResponse(String data){
        this.data = data;
    }

    @Override
    public String getRawData() {
        return data;
    }

    @Override
    public void setRawData(String data) {
        this.data = data;
    }
}
