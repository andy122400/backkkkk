package com.accton.newframework.utility.contants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Objects;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CompanyEnum {

    ATVN("VN", "ATVN"),
    ACCTON("AC", "Accton"),
    JOY_TECH("JT", "JoyTech");

    private final String code;


    private final String name;

    CompanyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @JsonCreator
    public static CompanyEnum forId(String id) {
        for (CompanyEnum distance : CompanyEnum.values()) {
            if (Objects.equals(distance.code, id)) {
                return distance;
            }
        }
        return null;
    }


}
