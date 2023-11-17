package com.accton.newframework.core.application.dto.response.screen.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ComponentEnum {

    PARENT("PARENT"),
    FORM("FORM"),
    TEXT("TEXT"),
    HEADER_TEXT("HEADER_TEXT"),
    TEXT_INPUT("TEXT_INPUT"),
    FILTER("FILTER"),
    BUTTON("BUTTON"),
    TAB("TAB"),
    TABLE("TABLE"),
    ;

    public final String code;

    ComponentEnum(String code) {
        this.code = code;
    }

    @JsonValue
    public String value() {
        return code;
    }
}
