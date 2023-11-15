package com.accton.newframework;

import com.accton.newframework.core.domain.identity.service.UserService;
import com.accton.newframework.core.domain.identity.model.RoleModel;
import com.accton.newframework.core.domain.identity.model.UserModel;
import com.accton.newframework.utility.contants.RoleConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;

@SpringBootApplication
public class NewframeworkApplication implements CommandLineRunner {

    public static void main(String[] args) {
        init();
        SpringApplication.run(NewframeworkApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        Set<RoleModel> roles = new HashSet<>();
        roles.add(new RoleModel(RoleConstant.USER_ROLE,"Normal User"));
        roles.add(new RoleModel(RoleConstant.ADMIN,"Administrator"));
        userService.addRoles(roles);
         userService.addUser(
                 UserModel.builder()
                         .userName("marco1")
                         .displayName("Marco Duong")
                         .roles(roles)
                         .password("Accton123")
                         .build()
         );
    }

    private static final TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(ZoneOffset.UTC);
    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;
    private static void init() {
        TimeZone.setDefault(DEFAULT_TIME_ZONE);
        Locale.setDefault(DEFAULT_LOCALE);
    }
}
