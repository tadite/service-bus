package edu.nc.servicebus.model.action;


import edu.nc.servicebus.datagrid.dao.ErrorDao;
import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.dao.ResponseDao;
import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.request.SoapRequest;
import edu.nc.servicebus.model.response.Response;
import edu.nc.servicebus.model.response.ResponseFilter;
import edu.nc.servicebus.model.response.XmlResponseFilter;
import edu.nc.servicebus.model.sender.Sender;
import edu.nc.servicebus.model.sender.SoapSender;
import edu.nc.servicebus.model.source.SoapSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component(value = "soapAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SoapAction implements Action{

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private ErrorDao errorDao;

    private List<String> requestParams;
    private ResponseFilter filter;
    private Request request;
    private Sender sender;

    private long initTime;
    private long responseTime;
    private long responseEndTime;

    public SoapAction(){
        initTime = System.currentTimeMillis();
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
        Response response = null;

        try {
            responseTime = System.currentTimeMillis();

            response = sender.send(request);

            responseEndTime = System.currentTimeMillis();
            responseDao.add(this.hashCode(), response.getRawData(),
                    new Date(responseTime), new Date(responseEndTime));

            if (filter != null) {
                response = filter.filter(response);
            }
        } catch (Exception e){
            errorDao.add(this.hashCode(), e.getMessage());
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

    @Override
    public int hashCode() {
        int result = (int) (initTime ^ (initTime >>> 32));
        //result = 31 * result + Action.class.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof SoapAction) &&
                (request.getUrl().equals(((SoapAction) obj).request.getUrl())) &&
                initTime == ((SoapAction) obj).initTime);
    }
}
