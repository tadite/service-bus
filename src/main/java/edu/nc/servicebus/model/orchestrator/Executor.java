package edu.nc.servicebus.model.orchestrator;

import edu.nc.servicebus.model.response.Response;

public interface Executor {
    Response executeAction(String name);
}
