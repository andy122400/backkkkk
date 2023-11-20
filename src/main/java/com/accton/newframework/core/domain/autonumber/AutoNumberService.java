package com.accton.newframework.core.domain.autonumber;

import com.accton.newframework.core.application.dto.response.AutoNumberResponse;
import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;

import java.util.List;

public interface AutoNumberService {

    List<AutoNumberResponse> list();

    AutoNumberResponse save(AutoNumberAdd event);

    void deleteAutoNumber(Long id);
}
