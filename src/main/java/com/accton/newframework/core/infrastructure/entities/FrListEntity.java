package com.accton.newframework.core.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "fr_list")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrListEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fr_list_id")
    private Long id;

    @Column(name = "name", length = 250,nullable = false)
    private String name;

    @Column(name = "category", length = 250,nullable = false)
    private String category;

    @Column(name = "description", length = 4000,nullable = false)
    private String description;

    @Column(name = "status", columnDefinition = "smallint default 0",nullable = false)
    private Integer status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private FrListDetailEntity detail;

}
