package edu.nc.servicebus.model.parser;

import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionType;
import edu.nc.servicebus.model.action.HttpAction;
import edu.nc.servicebus.model.action.SoapAction;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ActionSelector {

    private Map<ActionType, Supplier<Action>> actionTypes = new HashMap<>();

    public ActionSelector(){
        actionTypes.put(ActionType.REST, HttpAction::new);
        actionTypes.put(ActionType.SOAP, SoapAction::new);
    }

    public Action create(ActionType type){
        return actionTypes.get(type).get();
    }
}
