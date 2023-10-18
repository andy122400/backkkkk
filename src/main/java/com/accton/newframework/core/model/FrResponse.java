package com.accton.newframework.core.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter @Setter
public class FrResponse {    
    private Boolean fr_status;
    private List<FrFrontEnd> posts;
}