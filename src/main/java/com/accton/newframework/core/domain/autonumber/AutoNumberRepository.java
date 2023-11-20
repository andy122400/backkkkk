package com.accton.newframework.core.domain.autonumber;

import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;
import com.accton.newframework.core.domain.autonumber.model.AutoNumberModel;

import java.util.List;

public interface AutoNumberRepository {
    List<AutoNumberModel> list();

    AutoNumberModel save(AutoNumberAdd event);

    void deleteAutoNumber(Long id);
}
