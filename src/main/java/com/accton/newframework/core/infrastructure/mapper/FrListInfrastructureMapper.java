package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.frlist.model.FrListDetailModel;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.infrastructure.entities.FrListDetailEntity;
import com.accton.newframework.core.infrastructure.entities.FrListEntity;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class FrListInfrastructureMapper {

    public static FrListModel toDomainModel(FrListEntity entity) {
        if (entity == null) return null;
        FrListModel model = new FrListModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }

    public static FrListEntity toDbModel(FrListModel model){
        if (model==null) return null;
        return FrListEntity.builder().build();
    }

    public static FrListDetailModel toDomainModel(FrListDetailEntity entity) {
        if (entity == null) return null;
        FrListDetailModel model = new FrListDetailModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }

}
