package edu.nc.servicebus.model.executor;

import edu.nc.servicebus.datagrid.dao.ErrorDao;
import edu.nc.servicebus.datagrid.dao.LogDao;
import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.dao.ResponseDao;
import edu.nc.servicebus.datagrid.model.Error;
import edu.nc.servicebus.datagrid.model.Request;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.limiter.RateLimiterManager;
import edu.nc.servicebus.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionExecutor implements Executor {

    @Autowired
    private LogDao logDao;

    @Autowired
    private RequestDao requestDao;

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private ErrorDao errorDao;

    @Autowired
    RateLimiterManager rateLimiterManager;
    @Autowired
    ActionFactory actionFactory;

    @Override
    public Response executeAction(String name) {

        Action action = null;
        try {
            action = actionFactory.getAction(name);
        } catch (Exception e){}

        Response response = action.execute();

        addToLog(action);

        /*while (!rateLimiterManager.tryAcquire(name)){

        }*/
        return response;
    }

    private void addToLog(Action action){
        Request request = requestDao.findById(action.hashCode());
        edu.nc.servicebus.datagrid.model.Response dbResponse = responseDao.findById(action.hashCode());
        Error error = errorDao.findById(action.hashCode());

        int requestId = 0;
        int responseId = 0;
        int errorId = 0;

        if (request != null){
            requestId = request.getRequestId();
        }
        if (dbResponse != null){
            responseId = dbResponse.getResponseId();
        }
        if (error != null){
            errorId = error.getErrorId();
        }

        logDao.add(requestId, responseId, errorId);
    }
}
