package com.accton.newframework.core.application.converter;

import com.accton.newframework.utility.contants.FieldTypeEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FieldTypeEnumConverter implements Converter<String, FieldTypeEnum> {
    @Override
    public FieldTypeEnum convert(String source) {
        return FieldTypeEnum.forId(source);
    }
}
