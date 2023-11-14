package com.accton.newframework.core.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "fr_list_detail")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrListDetailEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fr_list_detail_id")
    private Long id;

    @Column(name = "parent_id", nullable = false)
    private Long parentId;

    @Column(name = "name", length = 250,nullable = false)
    private String name;

    @Column(name = "value", length = 250,nullable = false)
    private String value;

    @Column(name = "sort",columnDefinition = "int default 0", nullable = false)
    private Integer sort;

    @Column(name = "parent_entry")
    private Integer parentEntry;

    @Column(name = "description", length = 4000,nullable = false)
    private String description;

    @Column(name = "status", columnDefinition = "smallint default 0",nullable = false)
    private Integer status;


}
