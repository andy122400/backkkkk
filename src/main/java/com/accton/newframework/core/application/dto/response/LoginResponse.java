package com.accton.newframework.core.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("display_name")
    private String displayName;

}
