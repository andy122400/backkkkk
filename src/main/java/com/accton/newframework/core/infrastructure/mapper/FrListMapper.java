package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.frlist.model.FrListModel;
import com.accton.newframework.core.infrastructure.entities.FrListEntity;
import com.accton.newframework.core.infrastructure.entities.FrLogEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FrListMapper {

    public static FrListModel toDomainModel(FrListEntity entity) {
        if (entity == null) return null;
        return FrListModel.builder().build();
    }

    public static FrListEntity toDbModel(FrListModel model){
        if (model==null) return null;
        return FrListEntity.builder().build();
    }
}
