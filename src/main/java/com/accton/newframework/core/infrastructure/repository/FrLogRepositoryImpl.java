package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.frlog.FrLogRepository;
import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import com.accton.newframework.core.infrastructure.dao.FrLogDao;
import com.accton.newframework.core.infrastructure.entities.FrLogEntity;
import com.accton.newframework.core.infrastructure.mapper.FrLogMapper;
import org.springframework.stereotype.Service;

@Service
public class FrLogRepositoryImpl implements FrLogRepository {

    private final FrLogDao frLogDao;

    public FrLogRepositoryImpl(FrLogDao frLogDao) {
        this.frLogDao = frLogDao;
    }

    @Override
    public FrLogModel save(FrLogModel model) {
        FrLogEntity entity = FrLogMapper.fromModel(model);
        entity = frLogDao.save(entity);
        model.setId(entity.getId());
        return model;
    }

    @Override
    public FrLogModel getById(Long aLong) {
        return null;
    }
}
