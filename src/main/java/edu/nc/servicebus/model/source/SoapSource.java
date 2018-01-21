package edu.nc.servicebus.model.source;


public class SoapSource implements Source{

    private String url;

    public SoapSource(String url){
        this.url = url;
    }

    @Override
    public SourceType getType() {
        return SourceType.SOAP;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }
}
