package com.accton.newframework.core.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

import java.util.Map;

@Configuration
public class LdapConfig {

    private final LdapConfigProperties properties;
    public LdapConfig(LdapConfigProperties properties){
        this.properties = properties;
    }
    @Bean("JT-LdapTemplate")
    public LdapTemplate ldapTemplate1() {
        LdapContextSource contextSource = new LdapContextSource();
        setData(contextSource,properties.getJt());
        // Set other necessary LDAP configuration properties
        contextSource.afterPropertiesSet();
        return new LdapTemplate(contextSource);
    }
    @Bean("VN-LdapTemplate")
    public LdapTemplate ldapTemplate2() {
        LdapContextSource contextSource = new LdapContextSource();
        setData(contextSource,properties.getVn());
        // Set other necessary LDAP configuration properties
        contextSource.afterPropertiesSet();
        return new LdapTemplate(contextSource);
    }

    @Bean("AC-LdapTemplate")
    public LdapTemplate ldapTemplate3() {
        LdapContextSource contextSource = new LdapContextSource();
        setData(contextSource,properties.getAc());
        // Set other necessary LDAP configuration properties
        contextSource.afterPropertiesSet();
        return new LdapTemplate(contextSource);
    }

    private void setData(LdapContextSource contextSource,Map<String , String> map){
        String url = map.get("urls");
        String base = map.get("base");
        String username = map.get("username");
        String password = map.get("password");
        contextSource.setUrl(url);
        contextSource.setBase(base);
        contextSource.setUserDn(username);
        contextSource.setPassword(password);
    }

}
