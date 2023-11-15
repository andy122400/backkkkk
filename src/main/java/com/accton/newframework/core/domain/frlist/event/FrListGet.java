package com.accton.newframework.core.domain.frlist.event;

import com.accton.newframework.utility.contants.CriteriaEnum;
import com.accton.newframework.utility.contants.FieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class FrListGet {

    private CriteriaEnum matchIf;
    private FieldTypeEnum fieldType;
    private String content;
    private boolean isAdmin;

}
