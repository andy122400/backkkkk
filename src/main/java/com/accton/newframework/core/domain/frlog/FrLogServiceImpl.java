package com.accton.newframework.core.domain.frlog;

import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class FrLogServiceImpl implements FrLogService {

    private final FrLogRepository frLogRepository;
    public FrLogServiceImpl(FrLogRepository frLogRepository){
        this.frLogRepository = frLogRepository;
    }

    /**
     * Initial log
     * @param
     * @return
     */
    @Override
    public void initialLog(FrLogModel frLog) {
        clearLog(frLog);
        Date currentDate = new Date();
		frLog.setDtStart(currentDate);

		String unid = UUID.randomUUID().toString();
		frLog.setUnId(unid);
        frLog.setHostName("demohost");
        frLog.setServerIp("demoIP");
        frLog.setLoginUserLogon("demoUserLogin");
        frLog.setLoginPersonUid(1);
        frLog.setMsgType("F");
        System.out.println("initialLog....");
    }
    
    @Override
    public void setError(FrLogModel frLog,Exception e) {
        frLog.setErrorMsg(e.getMessage());
        System.out.println("setError....");
    }


    @Override
    public void setLog(FrLogModel frLog) {
        frLog.setDtEnd(new Date());
        System.out.println("Set Log....");
    }

    @Override
    public void saveLog(FrLogModel frLog) {
        //TODO define queue at here
        frLogRepository.save(frLog);
        System.out.println("saveLog....");
    }

    /**
     * Clear log data
     *
     * @param
     * @param frLog
     * @return
     */
    private void clearLog(FrLogModel frLog){
        frLog.resetData();
        System.out.println("clearLog....");
    }

}
