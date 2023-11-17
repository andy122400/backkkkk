package com.accton.newframework.core.application.dto.response.screen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;


@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class NewFrameWorkScreen {

    @JsonProperty("header")
    private Component header;

    @JsonProperty("body")
    private BodyScreen body;

}
