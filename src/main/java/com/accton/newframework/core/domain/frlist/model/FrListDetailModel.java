package com.accton.newframework.core.domain.frlist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FrListDetailModel {

    private Long id;
    private Long parentId;

    private String name;

    private String value;
    private Integer sort;
    private Integer parentEntry;

    private String description;

    private Integer status;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private Integer stateVoid ;
}
