package edu.nc.servicebus.model.executor;

import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.limiter.RateLimiterManager;
import edu.nc.servicebus.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionExecutor implements Executor {

    @Autowired
    RateLimiterManager rateLimiterManager;
    @Autowired
    ActionFactory actionFactory;

    @Override
    public Response executeAction(String name) {

        Action action = actionFactory.getAction(name);
        while (!rateLimiterManager.tryAcquire(name)){

        }
        return action.execute();
    }
}
