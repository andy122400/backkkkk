package com.accton.newframework.core.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.accton.newframework.core.model.FrLog;

@Component
public class FrLogServiceImpl implements FrLogService {

    @Autowired
    FrLog frLog;

    @Override
    public void initialLog() {
        Date currentDate = new Date();
		frLog.setDt_start(currentDate);
		
		String unid = UUID.randomUUID().toString();
		frLog.setUnid(unid);
    }
    
    @Override
    public void setError(Exception e) {
        frLog.setMsg_type("F");
        frLog.setError_msg(e.getMessage());
    }

    @Override
    public void setDetailMsg(String detail_msg) {
        frLog.setDetail_msg(detail_msg);
    }

    @Override
    public void setLog() {
        Date currentDate = new Date();
        frLog.setDt_end(currentDate);
        System.out.println(frLog.toString());
        System.out.println("Set Log....");
    }

}
