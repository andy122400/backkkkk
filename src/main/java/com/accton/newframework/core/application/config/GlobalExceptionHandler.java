package com.accton.newframework.core.application.config;

import com.accton.newframework.core.application.common.CommonResult;
import com.accton.newframework.utility.ApiException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLSyntaxErrorException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public Object handle(ApiException e) {
        return CommonResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.failed(message);
    }

    @ResponseBody
    @ExceptionHandler(value ={ MissingServletRequestParameterException.class, HttpMessageNotReadableException.class })
    public Object missParam(Exception e) {
        return CommonResult.failed(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public Object handleValidException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField()+fieldError.getDefaultMessage();
            }
        }
        return CommonResult.failed(message);
    }

    @ResponseBody
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public Object handleSQLSyntaxErrorException(SQLSyntaxErrorException e) {
        String message = e.getMessage();
        if (ObjectUtils.isEmpty(message) && message.contains("denied")) {
            message = "handleSQLSyntaxErrorException!";
        }
        return CommonResult.failed(message);
    }
}
