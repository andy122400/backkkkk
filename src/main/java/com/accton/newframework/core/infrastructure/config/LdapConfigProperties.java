package com.accton.newframework.core.infrastructure.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "ldap-config")
@ConfigurationPropertiesScan
@Getter
@Setter
public class LdapConfigProperties {

    private Map<String, String> jt;
    private Map<String, String> vn;
    private Map<String, String> ac;
}
