package com.accton.newframework.core.domain.autonumber.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AutoNumberAdd {

    private Long id;

    private String name;


    private String apiName;


    private String description;

    private Integer status;
}
