package edu.nc.servicebus.model.sender;

import edu.nc.servicebus.model.request.Request;
import edu.nc.servicebus.model.response.Response;

import java.io.IOException;

public interface Sender {
    Response send(Request request) throws Exception;
}
