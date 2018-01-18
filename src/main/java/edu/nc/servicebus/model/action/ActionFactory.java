package edu.nc.servicebus.model.action;

import java.io.IOException;
import java.util.Collection;

public interface ActionFactory {
    Action getAction(String name) throws Exception;
    Collection<String> getActionNames();
}
