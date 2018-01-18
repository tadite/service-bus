package edu.nc.servicebus.model.request;

import edu.nc.servicebus.model.source.Source;

public class AppendRequestFilter implements RequestFilter {


    private String stringToAppend;

    public AppendRequestFilter(String stringToAppend) {
        this.stringToAppend = stringToAppend;
    }

    @Override
    public Request filter(Request request) {
        request.setUrl(request.getUrl() + stringToAppend);
        return request;
    }
}
