package com.accton.newframework.core.application.logging;

import com.accton.newframework.core.domain.frlog.FrLogService;
import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// Spring AOP
@Aspect
@Component
public class LoggingAspect {

    private final FrLogService frLogService;

    public LoggingAspect(FrLogService frLogService) {
        this.frLogService = frLogService;
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */
    @Pointcut("@annotation(com.accton.newframework.core.application.logging.FrLoggable)")
    public void applicationControllerPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Around("applicationControllerPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("====================");
        System.out.println(joinPoint.getSignature().toString());
        System.out.println("====================");
        Object result = null;
        FrLogModel frLogModel = new FrLogModel();
        frLogService.initialLog(frLogModel);
        frLogModel.setObjectName(joinPoint.getSignature().getName());
        try {
            result = joinPoint.proceed(); // like UserController.get() .
        }
        catch (Exception e) {
            //Check the type of Annotation , Exception at here
            frLogService.setError(frLogModel,e);
        }finally {
            frLogService.setLog(frLogModel);
            frLogService.saveLog(frLogModel);
        }
        return result;
    }
}
