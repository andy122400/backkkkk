package com.accton.newframework.core.domain.undefine.fr.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FrFrontEnd {
    private String id;
    private String name;
    @NotBlank
    private String type;
    private String value;
}

