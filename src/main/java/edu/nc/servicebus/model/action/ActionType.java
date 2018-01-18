package edu.nc.servicebus.model.action;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum ActionType {

    @JsonProperty("http")
    REST,
    @JsonProperty("soap")
    SOAP
}
