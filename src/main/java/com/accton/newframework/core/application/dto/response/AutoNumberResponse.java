package com.accton.newframework.core.application.dto.response;

import com.accton.newframework.core.application.config.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AutoNumberResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("api_name")
    private String apiName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("create_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date createDate;

    @JsonProperty("create_by")
    private String createBy;

    @JsonProperty("update_date")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date updateDate;

    @JsonProperty("update_by")
    private String updateBy;

    @JsonProperty("state_void")
    private Integer stateVoid ;
}
