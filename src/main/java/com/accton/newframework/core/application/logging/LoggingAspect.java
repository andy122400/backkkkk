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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// Spring AOP
@Aspect
@Component
@Order(100)
public class LoggingAspect {

    private final Logger mLogger = LoggerFactory.getLogger(LoggingAspect.class);
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
        initLog(joinPoint);
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
            if (isFuncRoot(joinPoint)) {
                saveToDB();
            }
        }
        if (exception != null) {
            throw exception;
        }
        return result;
    }
    private boolean isFuncRoot(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().getDeclaringTypeName().equals(MDC.get("function_root"));
    }

    private void addLog(FrLogModel logModel) {
        try {
            List<FrLogModel> logs = objectMapper.readValue(MDC.get("log_data"), new TypeReference<List<FrLogModel>>() {
            });
            logModel.setUnId(getUnId());
            logs.add(logModel);
            MDC.put("log_data", objectMapper.writeValueAsString(logs));
        } catch (JsonProcessingException e) {
            mLogger.error(e.getMessage());
        }
    }

    private String getUnId() {
        return MDC.get("logId");
    }

    private void initLog(ProceedingJoinPoint joinPoint) {
        try {
            if (ObjectUtils.isEmpty(MDC.get("logId"))){
                String unid = UUID.randomUUID().toString();
                MDC.put("logId", unid);
                MDC.put("function_root", joinPoint.getSignature().getDeclaringTypeName());
                MDC.put("log_data", objectMapper.writeValueAsString(new ArrayList<FrLogModel>()));
            }
        } catch (JsonProcessingException e) {
            mLogger.error(e.getMessage());
        }
    }

    private void saveToDB() {
        try {
            List<FrLogModel> logs = objectMapper.readValue(MDC.get("log_data"), new TypeReference<List<FrLogModel>>() {
            });
            frLogService.saveLogs(logs);
            MDC.clear();
        } catch (Exception e) {
            mLogger.error(e.getMessage());
        }
    }
}
