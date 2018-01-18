package edu.nc.servicebus.model.parser;


import com.fasterxml.jackson.annotation.JsonProperty;
import edu.nc.servicebus.model.action.ActionType;

import java.util.List;
import java.util.Map;

public class ActionJsonEntity {

    @JsonProperty("url")
    private String url;
    @JsonProperty("type")
    private ActionType actionType;
    @JsonProperty("parameters")
    private Map<String, Object> parameters;
    @JsonProperty("expression")
    private String expression;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
