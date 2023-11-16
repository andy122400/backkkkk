package com.accton.newframework.core.application.converter;

import com.accton.newframework.utility.contants.CriteriaEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CriteriaEnumConverter implements Converter<String, CriteriaEnum> {

    @Override
    public CriteriaEnum convert(String source) {
        return CriteriaEnum.forId(source);
    }
}
