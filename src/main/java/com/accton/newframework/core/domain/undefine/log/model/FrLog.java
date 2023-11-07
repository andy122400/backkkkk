package com.accton.newframework.core.domain.undefine.log.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter @Setter
@ToString
public class FrLog {
    private String unid="";
    private String object_name = "";
    private String detail_msg = "";
    private String msg = "";
    private String error_msg = "";
    private String msg_type = "S";
    private Date dt_start;
    private Date dt_end;
    private String login_person_uid;
    private String login_user_logon;
    private String create_by;
}
