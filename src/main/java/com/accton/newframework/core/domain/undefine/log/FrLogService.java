package com.accton.newframework.core.domain.undefine.log;

public interface FrLogService {
    void initialLog();
    void setError(Exception e);
    void setDetailMsg(String detail_msg);
    void setLog();
}
