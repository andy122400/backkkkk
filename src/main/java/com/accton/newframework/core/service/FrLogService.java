package com.accton.newframework.core.service;

public interface FrLogService {
    void initialLog();
    void setError(Exception e);
    void setDetailMsg(String detail_msg);
    void setLog();
}
