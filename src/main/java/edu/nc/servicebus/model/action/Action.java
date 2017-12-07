package edu.nc.servicebus.model.action;

import edu.nc.servicebus.model.response.Response;

public interface Action {
    Response execute();
    void rollback();
}
