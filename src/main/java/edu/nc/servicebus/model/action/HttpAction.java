package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.request.RequestFilter;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.sender.Sender;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class HttpAction implements Action {

    private List<RequestFilter> requestFilters = new LinkedList<>();
    private List<ResponseFilter> responseFilters = new LinkedList<>();
    private Request request;
    private Sender sender;
    private Double rate;

    public HttpAction(Request request, Sender sender,
                      Collection<RequestFilter> requestFilters, Collection<ResponseFilter> responseFilters,
                      Double rate) {
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

        Response response = sender.send(request);

        for (ResponseFilter responseFilter : responseFilters){
            response = responseFilter.filter(response);
        }

        return response;
    }

    @Override
    public void rollback() {

    }

    @Override
    public Double getRate() {
        return rate;
    }
}
