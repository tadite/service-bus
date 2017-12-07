package edu.nc.servicebus.model.request;


public interface RequestFilter {
    Request filter(Request source);
}
