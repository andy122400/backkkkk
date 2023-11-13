package com.accton.newframework.utility;

import lombok.experimental.UtilityClass;

import java.util.Base64;

@UtilityClass
public class AppUtils {


    public static String decodeBase64(String encoded) {
        return new String(Base64.getDecoder().decode(encoded));
    }
}
