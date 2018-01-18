package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.request.AppendRequestFilter;
import edu.nc.servicebus.model.request.HttpRequest;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.request.RequestFilter;
import edu.nc.servicebus.model.response.JsonPathResponseFilter;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.sender.HttpSender;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.source.RestSource;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HttpAction implements Action {

    private List<RequestFilter> requestFilters;
    private List<ResponseFilter> responseFilters;
    private Request request;
    private Sender sender;

    public HttpAction(){
        requestFilters = new LinkedList<>();
        responseFilters = new LinkedList<>();
        sender = new HttpSender();
    }

    public HttpAction(Request request, Sender sender,
                      Collection<RequestFilter> requestFilters, Collection<ResponseFilter> responseFilters) {
        this.requestFilters = new LinkedList<>();
        this.responseFilters = new LinkedList<>();
        this.request = request;
        this.sender = sender;
        this.requestFilters.addAll(requestFilters);
        this.responseFilters.addAll(responseFilters);
    }

    @Override
    public Response execute() {
        for (RequestFilter requestFilter : requestFilters){
            request=requestFilter.filter(request);
        }

        Response response = null;
        try {
            response = sender.send(request);
        } catch (IOException e){
        }

        for (ResponseFilter responseFilter : responseFilters){
            response = responseFilter.filter(response);
        }

        return response;
    }

    @Override
    public void setRequest(String request) {
        requestFilters.add(new AppendRequestFilter(request));
    }

    @Override
    public void setUrl(String source) {
        request = new HttpRequest(new RestSource(source));
    }

    @Override
    public void setResponseFilter(String responseFilter) {
        responseFilters.add(new JsonPathResponseFilter(responseFilter));
    }
}
