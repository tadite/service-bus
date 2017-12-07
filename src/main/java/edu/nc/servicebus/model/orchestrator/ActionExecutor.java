package edu.nc.servicebus.model.orchestrator;

import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionExecutor implements Executor {

    @Autowired
    ActionFactory actionFactory;

    @Override
    public Response executeAction(String name) {
        return actionFactory.getAction(name).execute();
    }
}
