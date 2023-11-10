package com.accton.newframework.core.application.logging;

import com.accton.newframework.core.domain.frlog.FrLogService;
import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import com.accton.newframework.utility.ApiException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Spring AOP
@Aspect
@Component
public class LoggingAspect {

    private final FrLogService frLogService;
    private final ObjectMapper objectMapper;

    public LoggingAspect(FrLogService frLogService,
                         ObjectMapper objectMapper) {
        this.frLogService = frLogService;
        this.objectMapper = objectMapper;
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
        Exception exception = null;
        if (isFuncOfController(joinPoint)) {
            initLog();
        }
        Object result = null;
        FrLogModel frLogModel = new FrLogModel();
        frLogService.initialLog(frLogModel);
        frLogModel.setObjectName(joinPoint.getSignature().getName());
        try {
            result = joinPoint.proceed(); // like UserController.get() .
        } catch (Exception e) {
            //Check the type of Annotation , Exception at here
            frLogService.setError(frLogModel, e);
            exception = e;
        } finally {
            frLogService.setLog(frLogModel);
            addLog(frLogModel);
            if (isFuncOfController(joinPoint)) {
                saveToDB();
            }
        }
        if (exception != null) {
            if (isFuncOfController(joinPoint)) {
                throw new ApiException(exception.toString());
            }
            throw exception;
        }
        return result;
    }

    private boolean isFuncOfController(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName().startsWith("com.accton.newframework.core.application.controller");
    }

    private void addLog(FrLogModel logModel) {
        try {
            List<FrLogModel> logs = objectMapper.readValue(MDC.get("log_data"), new TypeReference<List<FrLogModel>>() {
            });
            logs.add(logModel);
            MDC.put("log_data", objectMapper.writeValueAsString(logs));
        } catch (JsonProcessingException e) {
            System.out.println(e);
        }
    }

    private void initLog() {
        try {
            String unid = UUID.randomUUID().toString();
            MDC.put("logId", unid);
            MDC.put("log_data", objectMapper.writeValueAsString(new ArrayList<FrLogModel>()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveToDB() {
        try {
            List<FrLogModel> logs = objectMapper.readValue(MDC.get("log_data"), new TypeReference<List<FrLogModel>>() {
            });
            frLogService.saveLogs(logs);
            MDC.clear();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
