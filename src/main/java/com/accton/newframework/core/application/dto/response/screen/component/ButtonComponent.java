package com.accton.newframework.core.application.dto.response.screen.component;

import com.accton.newframework.core.application.dto.response.screen.Component;
import com.accton.newframework.core.application.dto.response.screen.component.button.ButtonData;
import com.accton.newframework.core.application.dto.response.screen.type.ComponentEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ButtonComponent extends Component {
    public ButtonComponent(ButtonData data) {
        this.type = ComponentEnum.BUTTON;
        this.data = data;
    }
}
