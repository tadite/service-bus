package edu.nc.servicebus.model.parser;

import edu.nc.servicebus.datagrid.dao.RequestDao;
import edu.nc.servicebus.datagrid.dao.ResponseDao;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionType;
import edu.nc.servicebus.model.action.HttpAction;
import edu.nc.servicebus.model.action.SoapAction;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class ActionSelector {

    /*@Lazy
    @Autowired
    @Qualifier("httpAction")
    private HttpAction httpAction;

    @Lazy
    @Autowired
    @Qualifier("soapAction")
    private SoapAction soapAction;*/

    @Autowired
    @Qualifier("httpAction")
    private ObjectFactory<HttpAction> httpAction;

    @Autowired
    @Qualifier("soapAction")
    private ObjectFactory<SoapAction> soapAction;


    private Map<ActionType, ObjectFactory<Action>> actionTypes = new HashMap<>();

    public ActionSelector(){
        /*actionTypes.put(ActionType.REST);
        actionTypes.put(ActionType.SOAP);*/
    }

    public Action create(ActionType type){
        if (type.equals(ActionType.REST)){
            return httpAction.getObject();
        } else if (type.equals(ActionType.SOAP)){
            return soapAction.getObject();
        }
        //return actionTypes.get(type);
        return null;
    }
}
