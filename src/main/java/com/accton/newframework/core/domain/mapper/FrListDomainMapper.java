package com.accton.newframework.core.domain.mapper;

import com.accton.newframework.core.application.dto.response.FrListDetailResponse;
import com.accton.newframework.core.application.dto.response.FrListResponse;
import com.accton.newframework.core.domain.frlist.model.FrListDetailModel;
import com.accton.newframework.core.domain.frlist.model.FrListModel;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class FrListDomainMapper {

    public static FrListResponse toDto(FrListModel model){
        FrListResponse response = new FrListResponse();
        BeanUtils.copyProperties(model,response);
        return response;
    }

    public static FrListDetailResponse toDto(FrListDetailModel model){
        FrListDetailResponse response = new FrListDetailResponse();
        BeanUtils.copyProperties(model,response);
        return response;
    }
}
