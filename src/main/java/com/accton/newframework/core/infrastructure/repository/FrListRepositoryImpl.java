package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.frlist.FrListRepository;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.infrastructure.dao.FrListDao;
import com.accton.newframework.core.infrastructure.mapper.FrListMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrListRepositoryImpl implements FrListRepository {

    private final FrListDao frListDao;

    public FrListRepositoryImpl(FrListDao frListDao) {
        this.frListDao = frListDao;
    }

    @Override
    public FrListModel save(FrListModel model) {
        frListDao.save(FrListMapper.toDbModel(model));
        return model;
    }

    @Override
    public void saveAll(List<FrListModel> models) {
        frListDao.saveAll(models.stream().map(FrListMapper::toDbModel).collect(Collectors.toList()));
    }

    @Override
    public FrListModel getById(Long aLong) {
        return FrListMapper.toDomainModel(frListDao.findById(aLong).orElse(null));
    }
}
