package com.accton.newframework.utility.contants;

import java.util.Objects;

public enum FieldTypeEnum {
    ID("id"),
    NAME("name"),
    DESCRIPTION("description");

    private final String code;


    FieldTypeEnum(String code) {
        this.code = code;
    }

    public static FieldTypeEnum forId(String id) {
        for (FieldTypeEnum distance : FieldTypeEnum.values()) {
            if (Objects.equals(distance.code, id)) {
                return distance;
            }
        }
        return null;
    }
}
