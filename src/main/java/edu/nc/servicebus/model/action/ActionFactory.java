package edu.nc.servicebus.model.action;

import java.util.Collection;

public interface ActionFactory {
    Action getAction(String name);
    Collection<String> getActionNames();
}
