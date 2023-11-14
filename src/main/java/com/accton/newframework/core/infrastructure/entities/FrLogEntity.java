package com.accton.newframework.core.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fr_log")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrLogEntity extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fr_log_id")
    private Long id;

    @Column(name = "unid", length = 50,nullable = false)
    private String unid;

    @Column(name = "object_name", length = 80,nullable = false)
    private String objectName;

    @Column(name = "detail_msg", columnDefinition = "nvarchar(4000)")
    private String detailMsg;

    @Column(name = "msg", columnDefinition = "nvarchar(1000)")
    private String msg;

    @Column(name = "error_msg", columnDefinition = "nvarchar(4000)")
    private String errorMsg;

    @Column(name = "msg_type", columnDefinition = "nvarchar(2)",nullable = false)
    private String msgType;

    @Column(name = "start_date", columnDefinition = "datetime(3)",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date", columnDefinition = "datetime(3)",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "person_uid",nullable = false)
    private Integer personUid;

    @Column(name = "user_logon", length = 50,nullable = false)
    private String userLogon;

    @Column(name = "client_ip", length = 50)
    private String clientIp;

    @Column(name = "host_name", length = 50)
    private String hostName;

}
