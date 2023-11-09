package com.accton.newframework.core.domain.frlog.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class FrLogModel {

    private Long id;

    @JsonProperty("unid")
    private String unId ;

    @JsonProperty("object_name")
    private String objectName ;

    @JsonProperty("detail_msg")
    private String detailMsg;

    @JsonProperty("msg")
    private String msg ;

    @JsonProperty("error_msg")
    private String errorMsg ;

    @JsonProperty("msg_type")
    private String msgType = "S";

    @JsonProperty("start_date")
    private Date startDate;

    @JsonProperty("end_date")
    private Date endDate;

    @JsonProperty("login_person_uid")
    private Integer loginPersonUid;

    @JsonProperty("login_user_logon")
    private String loginUserLogon;

    @JsonProperty("create_by")
    private String createBy;

    @JsonProperty("server_ip")
    private String serverIp;

    @JsonProperty("host_name")
    private String hostName;

    public void resetData(){
        this.setCreateBy(null);
        this.setDetailMsg(null);
        this.setStartDate(null);
        this.setEndDate(null);
        this.setErrorMsg(null);
        this.setLoginPersonUid(null);
        this.setLoginUserLogon(null);
        this.setMsg(null);
        this.setMsgType(null);
        this.setObjectName(null);
        this.setUnId(null);
        this.setServerIp(null);
        this.setHostName(null);
    }

}
