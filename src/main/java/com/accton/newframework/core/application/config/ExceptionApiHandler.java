package com.accton.newframework.core.application.config;

import com.accton.newframework.utility.ApiException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(99)
public class ExceptionApiHandler {

    @Pointcut("within(com.accton.newframework.core.application.controller..*)")
    public void applicationControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("applicationControllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;
        try {
            result = joinPoint.proceed(); // like UserController.get() .
        } catch (Exception e) {
           throw new ApiException(e.toString());
        }
        return result;
    }

}
