package edu.nc.servicebus.model.source;

public class RestSource implements Source {

    private String url;

    public RestSource(String url) {
        this.url = url;
    }

    @Override
    public SourceType getType() {
        return SourceType.REST;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url= url;
    }
}
