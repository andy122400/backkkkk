package com.accton.newframework.core.domain.frlog;

import com.accton.newframework.core.domain.IRepository;
import com.accton.newframework.core.domain.frlog.model.FrLogModel;

import java.util.List;

public interface FrLogRepository extends IRepository<FrLogModel,Long> {

    void saveAll(List<FrLogModel> frLog);
    FrLogModel save(FrLogModel frLog);
}
