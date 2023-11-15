package com.accton.newframework.core.domain.undefine.fr;

import java.util.Map;

import com.accton.newframework.core.domain.undefine.fr.model.FrResponse;

public interface FrListService {
    FrResponse getResponse(String type) throws Exception;
    FrResponse setSaveValue(Map<String, Object> dataList) throws Exception;
}
