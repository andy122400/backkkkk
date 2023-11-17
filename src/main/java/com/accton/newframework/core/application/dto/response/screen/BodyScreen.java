package com.accton.newframework.core.application.dto.response.screen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyScreen {

    @JsonProperty("top")
    private Component top;

    @JsonProperty("body")
    private Component body;

    @JsonProperty("bottom")
    private Component bottom;
}
