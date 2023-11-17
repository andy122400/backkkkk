package com.accton.newframework.core.application.dto.response.screen.filter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class FilterOption {

    @JsonProperty("id")
    private DropDownEnum id;

    @JsonProperty("name")
    private String name;
}
