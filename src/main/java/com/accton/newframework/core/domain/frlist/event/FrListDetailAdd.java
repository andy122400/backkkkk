package com.accton.newframework.core.domain.frlist.event;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrListDetailAdd {

    private Long id;
    private Long parentId;

    private String name;

    private String value;
    private Integer sort;
    private Integer parentEntry;

    private String description;

    private Integer status;

}
