package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.autonumber.event.AutoNumberAdd;
import com.accton.newframework.core.domain.autonumber.model.AutoNumberModel;
import com.accton.newframework.core.infrastructure.entities.AutoNumberEntity;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class AutoNumberInfrastructureMapper {

    public static AutoNumberModel toDomainModel(AutoNumberEntity entity) {
        if (entity == null) return null;
        AutoNumberModel model = new AutoNumberModel();
        BeanUtils.copyProperties(entity, model);
        return model;
    }

    public static AutoNumberEntity toDbModel(AutoNumberAdd event){
        AutoNumberEntity entity = new AutoNumberEntity();
        BeanUtils.copyProperties(event,entity);
        return entity;
    }
}
