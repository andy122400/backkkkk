package com.accton.newframework.core.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrListDetailAddRequest {
    private String name;

    private String value;

    private String description;

    private Integer status;
    private Integer sort;
    private Integer parentEntry;
}
