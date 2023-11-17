package com.accton.newframework.core.application.dto.response.screen.action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum Action {
    SEND_REQUEST("SEND_REQUEST"),
    CLICK("CLICK"),

    ;
    private final String action;
    Action(String icon){
        this.action = icon;
    }
    @JsonValue
    public String value(){
        return action;
    }
}
