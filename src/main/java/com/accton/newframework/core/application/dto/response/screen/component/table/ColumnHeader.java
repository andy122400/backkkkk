package com.accton.newframework.core.application.dto.response.screen.component.table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ColumnHeader {

    @JsonProperty("key")
    private String key;

    @JsonProperty("label")
    private String label;
}
