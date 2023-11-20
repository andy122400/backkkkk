package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.frlist.FrListRepository;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListDetailAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.core.domain.frlist.model.FrListDetailModel;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.infrastructure.dao.FrListDao;
import com.accton.newframework.core.infrastructure.dao.FrListDetailDao;
import com.accton.newframework.core.infrastructure.entities.FrListDetailEntity;
import com.accton.newframework.core.infrastructure.entities.FrListEntity;
import com.accton.newframework.core.infrastructure.mapper.FrListInfrastructureMapper;
import com.accton.newframework.core.infrastructure.specification.SearchFilter;
import com.accton.newframework.core.infrastructure.specification.SearchFilterWrapper;
import com.accton.newframework.core.infrastructure.specification.SpecificationUtil;
import com.accton.newframework.utility.contants.StateVoid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FrListRepositoryImpl implements FrListRepository {

    private final FrListDao frListDao;
    private final FrListDetailDao frListDetailDao;

    public FrListRepositoryImpl(FrListDao frListDao,
                                FrListDetailDao frListDetailDao) {
        this.frListDao = frListDao;
        this.frListDetailDao = frListDetailDao;
    }

    public FrListModel save(FrListModel model) {
        return model;
    }

    public void saveAll(List<FrListModel> models) {
    }

    @Override
    public FrListModel getById(Long aLong) {
        return FrListInfrastructureMapper.toDomainModel(frListDao.findById(aLong).orElse(null));
    }

    @Override
    public List<FrListModel> getByFilter(FrListGet filter) {
        SearchFilterWrapper filterWrapper = new SearchFilterWrapper();
        SearchFilter.Operator operator = null;
        boolean isNeedFilter = true;

        switch (filter.getMatchIf()) {
            case STARTS_WITH:
                operator = SearchFilter.Operator.LIKEL;
                break;
            case SHOW_ALL:
                isNeedFilter = false;
                break;
            case CONTAINS:
                operator = SearchFilter.Operator.LIKE;
                break;
            case EQUALS:
                operator = SearchFilter.Operator.EQ;
                break;
            default:
                break;
        }
        if (isNeedFilter) {
            String filedName = null;
            switch (filter.getFieldType()) {
                case ID:
                    filedName = "id";
                    break;
                case NAME:
                    filedName = "name";
                    break;
                case DESCRIPTION:
                    filedName = "description";
                    break;
                default:
                    break;
            }
            filterWrapper.addFilter(SearchFilter.build(filedName, operator, filter.getContent()));
        }
        if (!filter.isAdmin()) {
            filterWrapper.addFilter(SearchFilter.build("stateVoid", SearchFilter.Operator.EQ, 0));
            filterWrapper.addFilter(SearchFilter.build("status", SearchFilter.Operator.EQ, 1));
        }
        List<FrListEntity> res = SpecificationUtil.query(filterWrapper, frListDao);
        return res.stream().map(FrListInfrastructureMapper::toDomainModel).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public FrListModel save(FrListAdd add) {
        if (add == null) return null;
        if (add.getId() == null) {
            FrListEntity entity = FrListInfrastructureMapper.toDbModel(add);
            entity = frListDao.save(entity);
            return FrListInfrastructureMapper.toDomainModel(entity);
        } else {
            Optional<FrListEntity> frListEntityOpt = frListDao.findById(add.getId());
            if (frListEntityOpt.isPresent()) {
                FrListEntity entity = frListEntityOpt.get();
                entity.setName(add.getName());
                entity.setCategory(add.getCategory());
                entity.setDescription(add.getDescription());
                entity.setStatus(add.getStatus());
                entity = frListDao.save(entity);
                return FrListInfrastructureMapper.toDomainModel(entity);
            }
        }
        return null;
    }

    @Override
    public List<FrListDetailModel> detail(Long id) {
        return frListDetailDao.findAllByParentIdAndStateVoid(id, 0, Sort.by("sort").descending().and(Sort.by("name")))
                .stream().map(FrListInfrastructureMapper::toDomainModel)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void deleteFrList(Long id) {
        frListDao.findById(id).ifPresent(e -> e.setStateVoid(StateVoid.INACTIVE.getCode()));
        frListDetailDao.findAllByParentId(id).forEach(e -> e.setStateVoid(StateVoid.INACTIVE.getCode()));
    }

    @Transactional
    @Override
    public void deleteFrListDetail(Long id) {
        frListDetailDao.findById(id).ifPresent(e -> e.setStateVoid(StateVoid.INACTIVE.getCode()));
    }

    @Transactional
    @Override
    public FrListDetailModel save(FrListDetailAdd add) {
        if (add == null) return null;
        if (add.getId() == null) {
            FrListDetailEntity entity = FrListInfrastructureMapper.toDbModel(add);
            entity = frListDetailDao.save(entity);
            return FrListInfrastructureMapper.toDomainModel(entity);
        } else {
            Optional<FrListDetailEntity> frEntityOpt = frListDetailDao.findById(add.getId());
            if (frEntityOpt.isPresent()) {
                FrListDetailEntity entity = frEntityOpt.get();
                entity.setName(add.getName());
                entity.setValue(add.getValue());
                entity.setSort(add.getSort());
                entity.setParentEntry(add.getParentEntry());
                entity.setDescription(add.getDescription());
                entity.setStatus(add.getStatus());
                entity = frListDetailDao.save(entity);
                return FrListInfrastructureMapper.toDomainModel(entity);
            }
        }
        return null;
    }
}
