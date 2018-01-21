package edu.nc.servicebus.model.action;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class JsonFileActionReader implements ActionReader {
    @Override
    public Action createByName(String name) {
        return null;
    }

    @Override
    public Collection<String> getAvailableActionNames() {
        return null;
    }
}
