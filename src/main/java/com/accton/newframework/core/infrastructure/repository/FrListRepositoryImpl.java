package com.accton.newframework.core.infrastructure.repository;

import com.accton.newframework.core.domain.frlist.FrListRepository;
import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListGet;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.infrastructure.dao.FrListDao;
import com.accton.newframework.core.infrastructure.entities.FrListEntity;
import com.accton.newframework.core.infrastructure.mapper.FrListInfrastructureMapper;
import com.accton.newframework.core.infrastructure.specification.SearchFilter;
import com.accton.newframework.core.infrastructure.specification.SearchFilterWrapper;
import com.accton.newframework.core.infrastructure.specification.SpecificationUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FrListRepositoryImpl implements FrListRepository {

    private final FrListDao frListDao;

    public FrListRepositoryImpl(FrListDao frListDao) {
        this.frListDao = frListDao;
    }

    public FrListModel save(FrListModel model) {
        frListDao.save(FrListInfrastructureMapper.toDbModel(model));
        return model;
    }

    public void saveAll(List<FrListModel> models) {
        frListDao.saveAll(models.stream().map(FrListInfrastructureMapper::toDbModel).collect(Collectors.toList()));
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

        switch (filter.getMatchIf()){
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
        if (isNeedFilter){
            String filedName = null;
            switch (filter.getFieldType()){
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
            filterWrapper.addFilter(SearchFilter.build(filedName, operator,filter.getContent()));
        }
        if (!filter.isAdmin()){
            filterWrapper.addFilter(SearchFilter.build("stateVoid", SearchFilter.Operator.EQ,0));
            filterWrapper.addFilter(SearchFilter.build("status", SearchFilter.Operator.EQ,1));
        }
        List<FrListEntity> res = SpecificationUtil.query(filterWrapper,frListDao);
        return res.stream().map(FrListInfrastructureMapper::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public FrListModel add(FrListAdd add) {
        FrListEntity entity = FrListEntity.builder()
                .name(add.getName())
                .category(add.getCategory())
                .description(add.getDescription())
                .status(add.getStatus())
                .build();
        entity = frListDao.save(entity);
        return FrListInfrastructureMapper.toDomainModel(entity);
    }
}
