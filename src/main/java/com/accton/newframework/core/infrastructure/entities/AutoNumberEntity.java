package com.accton.newframework.core.infrastructure.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "auto_number")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutoNumberEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_number_id")
    private Long id;


    @Column(name = "name", length = 250,nullable = false)
    private String name;

    @Column(name = "api_name", length = 250,nullable = false)
    private String apiName;

    @Column(name = "description", length = 4000,nullable = false)
    private String description;

    @Column(name = "status", columnDefinition = "smallint default 0",nullable = false)
    @Min(0)
    @Max(1)
    private Integer status;

}
