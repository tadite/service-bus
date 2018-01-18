package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;

public interface Action {
    Response execute();
    void setRequest(String request);
    void setUrl(String url);
    void setResponseFilter(String responseFilter);
}
