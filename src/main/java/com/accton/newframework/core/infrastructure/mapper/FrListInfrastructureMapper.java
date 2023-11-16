package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.frlist.event.FrListAdd;
import com.accton.newframework.core.domain.frlist.event.FrListDetailAdd;
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
    public static FrListEntity toDbModel(FrListAdd add){
        if (add==null) return null;
        return FrListEntity.builder()
                .id(add.getId())
                .name(add.getName())
                .category(add.getCategory())
                .description(add.getDescription())
                .status(add.getStatus())
                .build();
    }

    public static FrListDetailEntity toDbModel(FrListDetailAdd add){
        if (add == null) return null;
        return FrListDetailEntity.builder()
                .id(add.getId())
                .parentId(add.getParentId())
                .name(add.getName())
                .value(add.getValue())
                .sort(add.getSort())
                .parentEntry(add.getParentEntry())
                .description(add.getDescription())
                .status(add.getStatus())
                .build();
    }

    public static FrListDetailModel toDomainModel(FrListDetailEntity entity) {
        if (entity == null) return null;
        FrListDetailModel model = new FrListDetailModel();
        BeanUtils.copyProperties(entity,model);
        return model;
    }


}
