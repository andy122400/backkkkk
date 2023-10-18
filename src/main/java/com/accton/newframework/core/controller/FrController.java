package com.accton.newframework.core.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accton.newframework.core.model.FrFrontEnd;
import com.accton.newframework.core.model.FrResponse;
import com.accton.newframework.core.service.FrLogService;
import com.accton.newframework.core.service.FrService;

@RestController
@Validated
public class FrController {
    
    @Autowired
    FrService frService;
    @Autowired
    FrLogService frLogService;
    @Autowired
    FrResponse frResponse;

    @PostMapping("/FrGet")
    public FrResponse FrGet(@RequestBody @Valid FrFrontEnd value){
        //test2
        frLogService.initialLog();
        try {
            frService.getResponse(value.getType());
        } catch (Exception e) {
            frLogService.setError(e);
            e.printStackTrace();
        } finally {
            frLogService.setLog();
        }

        return frResponse; //單純回傳Object
        // return ResponseEntity.ok(frResponse); //回傳成功的Object
        // return ResponseEntity.status(HttpStatus.ACCEPTED).header("nn", "123").body(frResponse); //自訂回傳http狀態與Object
    }

    @PostMapping("/FrSave")
    public FrResponse FrSave(@RequestBody @NotEmpty(message = "request body must not be null.") Map<String, Object> value){

        frLogService.initialLog();
        try {
            frResponse = frService.setSaveValue(value);
        } catch (Exception e) {
            frLogService.setError(e);
            e.printStackTrace();
        } finally {
            frLogService.setLog();
        }

        return frResponse;
    }
    
    @PostMapping("/FrLogin")
    public FrResponse FrLogin(@RequestBody @NotEmpty(message = "request body must not be null.") Map<String, Object> value){

        frLogService.initialLog();
        try {
            System.out.println(value.size());
        } catch (Exception e) {
            frLogService.setError(e);
            e.printStackTrace();
        } finally {
            frLogService.setLog();
        }
        
        return null;
    }
}
