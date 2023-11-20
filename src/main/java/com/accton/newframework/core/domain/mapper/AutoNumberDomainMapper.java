package com.accton.newframework.core.domain.mapper;

import com.accton.newframework.core.application.dto.response.AutoNumberResponse;
import com.accton.newframework.core.domain.autonumber.model.AutoNumberModel;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class AutoNumberDomainMapper {


    public static AutoNumberResponse toDto(AutoNumberModel model) {
        if (model == null) return null;
        AutoNumberResponse response = new AutoNumberResponse();
        BeanUtils.copyProperties(model, response);
        return response;
    }

}
