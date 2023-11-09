package com.accton.newframework.core.infrastructure.mapper;

import com.accton.newframework.core.domain.frlog.model.FrLogModel;
import com.accton.newframework.core.infrastructure.entities.FrLogEntity;

public class FrLogMapper {

    public static FrLogEntity fromModel(FrLogModel model){
        return FrLogEntity.builder()
                .unid(model.getUnId())
                .objectName(model.getObjectName())
                .detailMsg(model.getDetailMsg())
                .msg(model.getMsg())
                .errorMsg(model.getErrorMsg())
                .msgType(model.getMsgType())
                .startDate(model.getDtStart())
                .endDate(model.getDtEnd())
                .personUid(model.getLoginPersonUid())
                .userLogon(model.getLoginUserLogon())
                .serverIp(model.getServerIp())
                .hostName(model.getHostName())
                .build();
    }
}
