package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import com.accton.newframework.core.infrastructure.entities.FrLogEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FrLogMapper {

    public static FrLogEntity toDbModel(FrLogModel model) {
        if (model == null) return null;
        return FrLogEntity.builder()
                .unid(model.getUnId())
                .objectName(model.getObjectName())
                .detailMsg(model.getDetailMsg())
                .msg(model.getMsg())
                .errorMsg(model.getErrorMsg())
                .msgType(model.getMsgType())
                .startDate(model.getStartDate())
                .endDate(model.getEndDate())
                .personUid(model.getLoginPersonUid())
                .userLogon(model.getLoginUserLogon())
                .clientIp(model.getClientIp())
                .hostName(model.getHostName())
                .build();
    }
}
