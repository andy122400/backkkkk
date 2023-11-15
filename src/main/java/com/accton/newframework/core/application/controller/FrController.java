package com.accton.newframework.core.application.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.accton.newframework.core.domain.undefine.fr.model.FrFrontEnd;
import com.accton.newframework.core.domain.undefine.fr.model.FrResponse;
import com.accton.newframework.core.domain.frlog.FrLogService;
import com.accton.newframework.core.domain.undefine.fr.FrListService;

@RestController
@Validated
public class FrController {
    
    @Autowired
    FrListService frService;
    @Autowired
    FrLogService frLogService;

    @PostMapping("/FrGet")
    public ResponseEntity<FrResponse> FrGet(@RequestBody @Valid FrFrontEnd value){
        
        FrResponse frResponse = new FrResponse();
        try {
            frResponse = frService.getResponse(value.getType());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // TODO save log to db ?
        }
        return ResponseEntity.ok(frResponse);
        // return ResponseEntity.status(HttpStatus.ACCEPTED).header("nn", "123").body(frResponse); //自訂回傳http狀態與Object
    }

    @PostMapping("/FrSave")
    public ResponseEntity<FrResponse> FrSave(@RequestBody @NotEmpty(message = "request body must not be null.") Map<String, Object> value){

        FrResponse frResponse = new FrResponse();
        try {
            // frResponse = frService.setSaveValue(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return ResponseEntity.ok(frResponse);
    }
    
    @PostMapping("/FrLogin")
    public ResponseEntity<FrResponse> FrLogin(@RequestBody @NotEmpty(message = "request body must not be null.") Map<String, Object> value){

        FrResponse frResponse = new FrResponse();
        try {
            System.out.println(value.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        
        return ResponseEntity.ok(frResponse);
    }
}
