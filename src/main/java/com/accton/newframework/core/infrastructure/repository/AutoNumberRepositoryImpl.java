package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.autonumber.AutoNumberRepository;
import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;
import com.accton.newframework.core.domain.autonumber.model.AutoNumberModel;
import com.accton.newframework.core.domain.mapper.AutoNumberDomainMapper;
import com.accton.newframework.core.infrastructure.dao.AutoNumberDao;
import com.accton.newframework.core.infrastructure.entities.AutoNumberEntity;
import com.accton.newframework.core.infrastructure.entities.FrListDetailEntity;
import com.accton.newframework.core.infrastructure.mapper.AutoNumberInfrastructureMapper;
import com.accton.newframework.core.infrastructure.mapper.FrListInfrastructureMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AutoNumberRepositoryImpl implements AutoNumberRepository {
    private final AutoNumberDao autoNumberDao;

    public AutoNumberRepositoryImpl(AutoNumberDao autoNumberDao) {
        this.autoNumberDao = autoNumberDao;
    }

    @Override
    public List<AutoNumberModel> list() {
        return autoNumberDao.findAll().stream().map(AutoNumberInfrastructureMapper::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public AutoNumberModel save(AutoNumberAdd event) {
        if (event.getId() == null) {
            // insert
            AutoNumberEntity entity = AutoNumberInfrastructureMapper.toDbModel(event);
            entity = autoNumberDao.save(entity);
            return AutoNumberInfrastructureMapper.toDomainModel(entity);

        } else {
            // update
            Optional<AutoNumberEntity> entityOpt = autoNumberDao.findById(event.getId());
            if (entityOpt.isPresent()) {
                AutoNumberEntity entity = entityOpt.get();
                entity.setName(event.getName());
                entity.setApiName(event.getApiName());
                entity.setDescription(event.getDescription());
                entity.setStatus(event.getStatus());
                entity = autoNumberDao.save(entity);
                return AutoNumberInfrastructureMapper.toDomainModel(entity);
            }
        }
        return null;
    }

    @Override
    public void deleteAutoNumber(Long id) {
        autoNumberDao.deleteById(id);
    }
}
