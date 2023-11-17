package com.accton.newframework.core.application.dto.response.screen.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SendRequestMethod {

    GET("GET"),
    POST("POST"),

    ;
    public final String code ;

    SendRequestMethod(String code){
        this.code = code;
    }

    @JsonValue
    public String getCode(){
        return code;
    }
}
