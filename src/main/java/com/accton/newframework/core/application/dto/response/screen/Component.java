package com.accton.newframework.core.application.dto.response.screen;

import com.accton.newframework.core.application.dto.response.screen.action.OnPress;
import com.accton.newframework.core.application.dto.response.screen.type.ComponentEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Component {

    @JsonProperty("type")
    protected ComponentEnum type;

    @JsonProperty("data")
    protected Object data;

}
