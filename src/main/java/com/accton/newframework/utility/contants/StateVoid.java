package com.accton.newframework.utility.contants;

import lombok.Getter;

@Getter
public enum StateVoid {
    INACTIVE(1) , ACTIVE(0);
    private final int code;
    StateVoid(int code){
        this.code = code;
    }

}
