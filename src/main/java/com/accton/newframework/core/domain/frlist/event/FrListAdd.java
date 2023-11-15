package com.accton.newframework.core.domain.frlist.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FrListAdd {
    private String name;

    private String category;

    private String description;

    private Integer status;
}
