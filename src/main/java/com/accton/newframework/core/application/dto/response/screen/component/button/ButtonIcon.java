package com.accton.newframework.core.application.dto.response.screen.component.button;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonIgnoreProperties(ignoreUnknown = true)
public enum ButtonIcon {

    ADD("ADD"),
    DELETE("DELETE"),
    SAVE("SAVE"),
    CLOSE("CLOSE"),
    REFRESH("REFRESH"),
    ;
    private final String icon;
    ButtonIcon(String icon){
        this.icon = icon;
    }
    @JsonValue
    public String value(){
        return icon;
    }
}
