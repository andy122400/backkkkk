package com.accton.newframework.core.infrastructure.config;

import com.accton.newframework.utility.SecurityUtils;
import com.accton.newframework.utility.contants.Constants;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@EnableJpaAuditing
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtils.getCurrentUserLogin().orElse(Constants.SYSTEM));
    }

}
