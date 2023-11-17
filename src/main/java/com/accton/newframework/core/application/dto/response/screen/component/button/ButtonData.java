package com.accton.newframework.core.application.dto.response.screen.component.button;

import com.accton.newframework.core.application.dto.response.screen.action.OnPress;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ButtonData {

    @JsonProperty("text")
    private String text;

    @JsonProperty("icon")
    private ButtonIcon icon;

    @JsonProperty("onPress")
    protected OnPress onPress;
}
