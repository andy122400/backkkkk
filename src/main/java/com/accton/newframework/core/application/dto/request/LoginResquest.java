package com.accton.newframework.core.application.dto.request;

import com.accton.newframework.utility.contants.CompanyEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter @Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResquest {

    @JsonProperty("user_name")
    @NotEmpty
    private String userName;

    @JsonProperty("password")
    @NotEmpty
    private String password;


    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @NotNull
    @JsonProperty("company")
    private CompanyEnum company;

}
