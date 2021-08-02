package com.example.readingisgood.config;

import com.example.readingisgood.config.audit.AuditorAwareImpl;
import com.example.readingisgood.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider(AuthService authService) {
        return new AuditorAwareImpl(authService);
    }

}
