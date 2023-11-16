package com.accton.newframework.utility.contants;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Objects;

public enum CriteriaEnum {

    STARTS_WITH("starts_with"),
    SHOW_ALL("show_all"),
    CONTAINS("contains"),
    EQUALS("equals");

    private final String code;



    CriteriaEnum(String code) {
        this.code = code;
    }

    @JsonCreator
    public static CriteriaEnum forId(String id) {
        for (CriteriaEnum distance : CriteriaEnum.values()) {
            if (Objects.equals(distance.code, id)) {
                return distance;
            }
        }
        return null;
    }
}
