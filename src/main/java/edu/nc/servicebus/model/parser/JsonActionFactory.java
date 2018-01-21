package edu.nc.servicebus.model.parser;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nc.servicebus.model.action.Action;
import edu.nc.servicebus.model.action.ActionFactory;
import edu.nc.servicebus.model.action.ActionReader;
import edu.nc.servicebus.model.action.HttpAction;
import edu.nc.servicebus.model.limiter.RateLimiterManager;
import edu.nc.servicebus.model.parser.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Component
public class JsonActionFactory implements ActionFactory{

    @Autowired
    private ActionSelector actionSelector;
    @Autowired
    private JsonReader jsonReader;
    @Autowired
    RateLimiterManager rateLimiterManager;
    @Autowired
    ActionReader actionReader;

    public JsonActionFactory(@Autowired JsonReader jsonReader){
        this.jsonReader = jsonReader;
        this.actionSelector = new ActionSelector();
    }

    @Override
    public Action getAction(String name) throws Exception{
        String jsonAction = jsonReader.getJsonAction(name);

        ObjectMapper objectMapper = new ObjectMapper();
        ActionJsonEntity actionEntity = (ActionJsonEntity) objectMapper.readValue(
                jsonAction, ActionJsonEntity.class);

        Action action = getActionFromSelector(actionEntity);

        //rateLimiterManager.createRateLimiterIfAbsent(name, action.getRate());

        return action;
    }

    private Action getActionFromSelector(ActionJsonEntity actionJsonEntity) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Action action = actionSelector.create(actionJsonEntity.getActionType());

        setFieldsByReflection(actionJsonEntity, action);
        return action;
    }

    private void setFieldsByReflection(ActionJsonEntity actionEntity, Action action) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Class<? extends Action> actionClass = action.getClass();
        setUrl(actionClass, action, actionEntity.getUrl());
        setParameters(actionClass, action, actionEntity.getParameters());
        setExpression(actionClass, action, actionEntity.getExpressions());
    }

    private void setUrl(Class<? extends Action> actionClass, Action action, String url) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Method method = actionClass.getMethod("setUrl", String.class);
        method.invoke(action, url);
    }

    private void setParameters(Class<? extends Action> actionClass, Action action, Map<String, Object> params) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Method method = actionClass.getMethod("setParameter", String.class);
        for (Map.Entry<String, Object> param : params.entrySet()){
            method.invoke(action, param.getValue());
        }
    }

    private void setExpression(Class<? extends Action> actionClass, Action action, String expression) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{

        Method method = actionClass.getMethod("setResponseFilter", String.class);
        method.invoke(action, expression);
    }

    @Override
    public Collection<String> getActionNames() {
        return null;
    }
}
