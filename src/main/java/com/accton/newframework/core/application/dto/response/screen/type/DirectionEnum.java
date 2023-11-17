package com.accton.newframework.core.application.dto.response.screen.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DirectionEnum {

    VERTICAL("VERTICAL"),
    HORIZONTAL("HORIZONTAL"),

    ;
    public final String code ;

    DirectionEnum(String code){
        this.code = code;
    }

    @JsonValue
    public String getCode(){
        return code;
    }
}
