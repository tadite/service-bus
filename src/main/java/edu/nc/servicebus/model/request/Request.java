package edu.nc.servicebus.model.request;

import edu.nc.servicebus.model.source.Source;

public interface Request {
    String getUrl();
    void setUrl(String url);
    Source getSource();
    void setSource(Source source);
}
