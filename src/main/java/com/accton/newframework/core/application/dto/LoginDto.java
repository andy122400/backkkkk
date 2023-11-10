package com.accton.newframework.core.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDto {

    @JsonProperty("user_name")
    @NotEmpty
    private String userName;

    @JsonProperty("password")
    @NotEmpty
    private String password;

    @JsonProperty("is_login_ad")
    @NotNull
    private Boolean isLoginAd;

}
