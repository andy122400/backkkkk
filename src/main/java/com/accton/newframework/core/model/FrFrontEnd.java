package com.accton.newframework.core.model;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class FrFrontEnd {
    private String id;
    private String name;
    @NotBlank
    private String type;
    private String value;
}

