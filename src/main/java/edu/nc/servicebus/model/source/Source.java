package edu.nc.servicebus.model.source;

public interface Source {
    SourceType getType();
    String getUrl();
    void setUrl(String url);
}
