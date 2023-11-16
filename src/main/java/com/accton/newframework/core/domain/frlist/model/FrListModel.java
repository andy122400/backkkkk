package com.accton.newframework.core.domain.frlist.model;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrListModel {

    private Long id;

    private String name;

    private String category;

    private String description;

    private Integer status;

    private Date createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private Integer stateVoid ;
}
