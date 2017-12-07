package edu.nc.servicebus.model.response;

public interface ResponseFilter {
    Response filter(Response sourceResult);
}
