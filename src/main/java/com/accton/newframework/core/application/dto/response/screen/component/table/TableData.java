package com.accton.newframework.core.application.dto.response.screen.component.table;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TableData<T> {

    @JsonProperty("headers")
    private List<ColumnHeader> headers;

    @JsonProperty("data")
    private List<T> data;

}
