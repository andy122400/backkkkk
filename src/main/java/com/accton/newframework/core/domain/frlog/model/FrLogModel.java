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

    @JsonProperty("client_ip")
    private String clientIp;

    @JsonProperty("host_name")
    private String hostName;

    public void resetData(){
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
        this.setClientIp(null);
        this.setHostName(null);
    }

}
