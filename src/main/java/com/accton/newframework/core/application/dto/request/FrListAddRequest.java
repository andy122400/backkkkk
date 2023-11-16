package com.accton.newframework.core.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrListAddRequest {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("category")
    @NotBlank
    private String category;

    @JsonProperty("description")
    @NotBlank
    private String description;

    @JsonProperty("status")
    @NotNull
    @Min(value = 0,message = " must greater than or equal 0")
    @Max(value = 1,message = " must less than or equal 1")
    private Integer status;

}
