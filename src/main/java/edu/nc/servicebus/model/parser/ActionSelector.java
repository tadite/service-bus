package edu.nc.servicebus.model.parser;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionType;
import edu.nc.servicebus.model.action.HttpAction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class ActionSelector {

    private Map<ActionType, Supplier<Action>> actionTypes = new HashMap<>();
    {
        actionTypes.put(ActionType.REST, HttpAction::new);
    }

    public Action create(ActionType type){
        return actionTypes.get(type).get();
    }
}
