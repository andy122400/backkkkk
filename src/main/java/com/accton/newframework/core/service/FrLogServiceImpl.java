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

    /**
     * Initial log
     * @param
     * @return
     */
    @Override
    public void initialLog() {
        clearLog();
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

    /**
     * Clear log data
     * @param
     * @return
     */
    private void clearLog(){
        frLog.setCreate_by(null);
        frLog.setDetail_msg(null);
        frLog.setDt_end(null);
        frLog.setDt_start(null);
        frLog.setError_msg(null);
        frLog.setLogin_person_uid(null);
        frLog.setLogin_user_logon(null);
        frLog.setMsg(null);
        frLog.setMsg_type(null);
        frLog.setObject_name(null);
        frLog.setUnid(null);
    }

}
