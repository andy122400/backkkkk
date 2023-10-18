package com.accton.newframework.core.service;

import java.util.Map;

import com.accton.newframework.core.model.FrResponse;

public interface FrService {
    FrResponse getResponse(String type) throws Exception;
    FrResponse setSaveValue(Map<String, Object> dataList) throws Exception;
}
