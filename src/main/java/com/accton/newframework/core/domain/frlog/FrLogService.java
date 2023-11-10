package com.accton.newframework.core.domain.frlog;


import com.accton.newframework.core.domain.frlog.model.FrLogModel;

import java.util.List;

public interface FrLogService {
    void initialLog(FrLogModel frLog);
    void setError(FrLogModel frLog,Exception e);
    void setLog(FrLogModel frLog);
    void saveLog(FrLogModel frLog);
    void saveLogs(List<FrLogModel> frLog);
}
