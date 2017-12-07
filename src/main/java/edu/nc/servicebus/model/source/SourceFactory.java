package edu.nc.servicebus.model.source;

public interface SourceFactory {
    Source getSource(String name);
}
