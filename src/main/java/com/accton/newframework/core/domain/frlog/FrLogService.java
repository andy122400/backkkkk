package com.accton.newframework.core.domain.frlog;


import com.accton.newframework.core.domain.frlog.model.FrLogModel;

public interface FrLogService {
    void initialLog(FrLogModel frLog);
    void setError(FrLogModel frLog,Exception e);
    void setLog(FrLogModel frLog);
    void saveLog(FrLogModel frLog);
}
