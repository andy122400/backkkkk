package com.accton.newframework.core.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
@EntityListeners(AuditingEntityListener.class)
public class AbstractAuditingEntity implements Serializable {

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", updatable = false, columnDefinition = "datetime(3)")
    protected Date createDate;

    @CreatedBy
    @Column(name = "create_by", length = 50, updatable = false)
    protected String createBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", columnDefinition = "datetime(3)")
    protected Date updateDate;


    @LastModifiedBy
    @Column(name = "update_by", length = 50)
    protected String updateBy;

    @Column(name = "state_void", columnDefinition = "smallint default 0 not null")
    protected Integer stateVoid = 0;

    @PrePersist
    private void setInsert() {
        updateBy = null;
        updateDate = null;
        createDate = new Date();
    }

    @PreUpdate
    private void setUpdate() {

    }
}