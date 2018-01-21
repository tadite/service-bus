package edu.nc.servicebus.model.request;


import edu.nc.servicebus.model.source.Source;

public class SoapRequest implements Request{

    private String url;
    private Source source;

    public SoapRequest(Source source){
        this.source = source;
        this.url = source.getUrl();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public void setSource(Source source) {
        this.source = source;
    }
}
