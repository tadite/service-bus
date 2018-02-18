package edu.nc.servicebus.model.action;

import edu.nc.servicebus.datagrid.dao.ResponseDao;
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
import java.util.*;

public class HttpAction implements Action {

    private List<RequestFilter> requestFilters;
    private List<ResponseFilter> responseFilters;
    private Request request;
    private Sender sender;
    private Double rate;

    public HttpAction(){
        requestFilters = new LinkedList<>();
        responseFilters = new LinkedList<>();
        sender = new HttpSender();
    }

    public HttpAction(Request request, Sender sender,
                      Collection<RequestFilter> requestFilters, Collection<ResponseFilter> responseFilters, Double rate){
        this.requestFilters = new LinkedList<>();
        this.responseFilters = new LinkedList<>();
        this.request = request;
        this.sender = sender;
        this.requestFilters.addAll(requestFilters);
        this.responseFilters.addAll(responseFilters);
        this.rate=rate;
    }

    @Override
    public Response execute() {
        for (RequestFilter requestFilter : requestFilters){
            request=requestFilter.filter(request);
        }

        Response response = null;

        response = sender.send(request);

        for (ResponseFilter responseFilter : responseFilters){
            response = responseFilter.filter(response);
        }

        return response;
    }

    @Override
    public void setParameter(String parameter) {
        requestFilters.add(new AppendRequestFilter(parameter));
    }

    @Override
    public Double getRate() {
        return rate;
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
