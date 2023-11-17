package com.accton.newframework.core.application.dto.response.screen.action;

import com.accton.newframework.core.application.dto.response.screen.type.SendRequestMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OnPress {

    @JsonProperty("action")
    private Action action;

    @JsonProperty("url")
    private String url;

    @JsonProperty("method")
    private SendRequestMethod method;

    @JsonProperty("action_name")
    private String actionName;

    @JsonProperty("data")
    private Object data;
}
