package com.accton.newframework.core.domain.frlog;

import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import com.accton.newframework.utility.HttpUtil;
import com.accton.newframework.utility.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
		frLog.setStartDate(currentDate);
        frLog.setHostName(HttpUtil.getHostName());
        frLog.setClientIp(HttpUtil.getClientIP());
        frLog.setLoginUserLogon(SecurityUtils.getUserLogon());
        frLog.setLoginPersonUid(-1);
        SecurityUtils.getCurrentUserId().ifPresent(s -> frLog.setLoginPersonUid(Integer.parseInt(s)));
        frLog.setMsgType("S");
        System.out.println("initialLog....");
    }
    
    @Override
    public void setError(FrLogModel frLog,Exception e) {
        frLog.setMsgType("F");
        frLog.setMsg(e.getMessage());
        frLog.setErrorMsg(e.toString());
        System.out.println("setError....");
    }


    @Override
    public void setLog(FrLogModel frLog) {
        frLog.setEndDate(new Date());
        System.out.println("Set Log....");
    }

    @Override
    public void saveLog(FrLogModel frLog) {
        //TODO define queue at here
        frLogRepository.save(frLog);
        System.out.println("saveLog....");
    }

    @Override
    public void saveLogs(List<FrLogModel> frLog) {
        frLogRepository.saveAll(frLog);
        System.out.println("save all log....");
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
