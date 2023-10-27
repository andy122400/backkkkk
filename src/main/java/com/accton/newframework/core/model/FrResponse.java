package com.accton.newframework.core.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class FrResponse {    
    private Boolean fr_status;
    private List<FrFrontEnd> posts;
}