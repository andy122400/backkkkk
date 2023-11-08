package com.accton.newframework.core.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable {

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date",updatable = false, columnDefinition = "datetime(3)")
    private Date createDate;

    @CreatedBy
    @Column(name = "create_by", length = 50, updatable = false)
    private String createBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", columnDefinition = "datetime(3)")
    private Date updateDate;


    @LastModifiedBy
    @Column(name = "update_by", length = 50)
    private String updateBy;

    @Column(name = "state_void", columnDefinition = "smallint default 0 not null")
    private Integer stateVoid = 0;

}