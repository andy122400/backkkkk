package com.accton.newframework.core.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class FrListResponse {

    @JsonProperty("fr_list_id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category")
    private String category;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonProperty("create_by")
    private String createBy;

    @JsonProperty("update_date")
    private Date updateDate;

    @JsonProperty("update_by")
    private String updateBy;

    @JsonProperty("state_void")
    private Integer stateVoid ;

}
