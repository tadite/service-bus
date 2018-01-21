package edu.nc.servicebus.model.action;


import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.request.SoapRequest;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.response.XmlResponseFilter;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.sender.SoapSender;
import edu.nc.servicebus.model.source.SoapSource;

import java.io.IOException;
import java.util.*;

public class SoapAction implements Action{

    private List<String> requestParams;
    private ResponseFilter filter;
    private Request request;
    private Sender sender;

    public SoapAction(){
        requestParams = new LinkedList<>();
    }

    @Override
    public Response execute() {
        String requestBody = "";

        Iterator<String> iterator = requestParams.iterator();
        while (iterator.hasNext()){
            String param = iterator.next();
            requestBody += param;
            if (iterator.hasNext()){
                requestBody += "&";
            }
        }

        sender = new SoapSender(requestBody);

        Response response = sender.send(request);

        if (filter != null) {
            response = filter.filter(response);
        }

        return response;
    }

    @Override
    public void setParameter(String parameter) {
        requestParams.add(parameter);
    }

    @Override
    public void setUrl(String url) {
        request = new SoapRequest(new SoapSource(url));
    }

    @Override
    public Double getRate() {
        return null;
    }

    @Override
    public void setResponseFilter(String responseFilter) {
        if (!responseFilter.trim().equals("") && responseFilter != null) {
            this.filter = new XmlResponseFilter(responseFilter);
        }
    }
}
