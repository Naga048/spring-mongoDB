package com.poc.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAuditing 
implements AuditorAware<String> 
{

    @Override
    public Optional<String> getCurrentAuditor() {
        String uname = "Naga";// User name  should be fetched from logged in user
        return Optional.of(uname);
    }
}
