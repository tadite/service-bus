package edu.nc.servicebus.model.sender;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;

public interface Sender {
    Response send(Request request);
}
