package com.accton.newframework.core.application.logging;
import java.lang.annotation.*;
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FrLoggable {
}
