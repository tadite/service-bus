package edu.nc.servicebus.model.action;

import edu.nc.servicebus.datagrid.dao.ErrorDao;
import edu.nc.servicebus.datagrid.dao.RequestDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.*;

@Component(value = "httpAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpAction implements Action {

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private ErrorDao errorDao;

    private List<RequestFilter> requestFilters;
    private List<ResponseFilter> responseFilters;
    private Request request;
    private Sender sender;
    private Double rate;

    private long initTime;
    private long responseTime;
    private long responseEndTime;

    public HttpAction(){
        this.initTime = System.currentTimeMillis();
        this.requestFilters = new LinkedList<>();
        this.responseFilters = new LinkedList<>();
        this.sender = new HttpSender();
        this.rate = Double.valueOf(100);

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
            request = requestFilter.filter(request);
        }

        Response response = null;

        try {
            responseTime = System.currentTimeMillis();

            response = sender.send(request);

            for (ResponseFilter responseFilter : responseFilters) {
                response = responseFilter.filter(response);
            }
        } catch (Exception e){
            errorDao.add(this.hashCode(), e.getMessage());
        } finally {
            responseEndTime = System.currentTimeMillis();
            responseDao.add(this.hashCode(), response.getRawData(),
                    new Date(responseTime), new Date(responseEndTime));
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

    @Override
    public int hashCode() {
        int result = (int) (initTime ^ (initTime >>> 32));
        //result = 31 * result + Action.class.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        return ((obj instanceof HttpAction) &&
                (request.getUrl().equals(((HttpAction) obj).request.getUrl())) &&
                initTime == ((HttpAction) obj).initTime);
    }
}
