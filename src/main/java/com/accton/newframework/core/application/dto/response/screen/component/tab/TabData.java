package com.accton.newframework.core.application.dto.response.screen.component.tab;

import com.accton.newframework.core.application.dto.response.screen.ListDirectionComponent;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TabData {

    @JsonProperty("label")
    private String label;

    @JsonProperty("children")
    private ListDirectionComponent children;

}
