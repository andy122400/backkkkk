package com.accton.newframework.core.application.dto.request;

import com.accton.newframework.utility.contants.CriteriaEnum;
import com.accton.newframework.utility.contants.FieldTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrListGetRequest {

    @JsonProperty("content")
    private String content;

    @JsonProperty("field_type")
    private FieldTypeEnum fieldType;

    @JsonProperty("match_if")
    private CriteriaEnum matchIf;

}
