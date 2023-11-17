package com.accton.newframework.core.application.dto.response.screen.filter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DropDownEnum {
    NAME("name"),
    DESCRIPTION("description"),
    ID("id"),
    STARTS_WITH("starts_with"),
    SHOW_ALL("show_all"),
    CONTAINS("contains"),
    END_WITH("end_with"),
    EQUALS("equals"),
    ;

    private final String code ;
    DropDownEnum(String code){
        this.code = code;
    }

    @JsonValue
    public String value(){
        return code;
    }
}
