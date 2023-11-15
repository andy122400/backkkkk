package com.accton.newframework.core.domain.frlist.event;

import com.accton.newframework.utility.contants.CriteriaEnum;
import com.accton.newframework.utility.contants.FieldTypeEnum;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

@Getter
public class FrListGet {

    private final CriteriaEnum matchIf;
    private final FieldTypeEnum fieldType;
    private final String content;
    private final boolean isAdmin;


    public FrListGet(CriteriaEnum matchIf, FieldTypeEnum fieldType, String content, boolean isAdmin) throws Exception {
        this.matchIf = matchIf;
        this.fieldType = fieldType;
        this.content = content;
        this.isAdmin = isAdmin;
        if (matchIf!= CriteriaEnum.SHOW_ALL && ObjectUtils.isEmpty(content)){
            throw new Exception("content must be not null when matchIf different show_all");
        }
    }
}
