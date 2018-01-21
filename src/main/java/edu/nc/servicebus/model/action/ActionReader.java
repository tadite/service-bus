package edu.nc.servicebus.model.action;

import java.util.Collection;

public interface ActionReader {
    Action createByName(String name);
    Collection<String> getAvailableActionNames();
}
