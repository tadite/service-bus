package edu.nc.servicebus.model.executor;

import edu.nc.servicebus.model.response.Response;

public interface Executor {
    Response executeAction(String name);
}
