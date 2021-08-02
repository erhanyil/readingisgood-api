package com.example.readingisgood.config.audit;

import com.example.readingisgood.model.Customer;
import com.example.readingisgood.service.AuthService;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    private final AuthService authService;

    public AuditorAwareImpl(AuthService authService) {
        this.authService = authService;
    }

    private String getCurrentUserId() {
        try {
            Customer customer = this.authService.credential();
            return customer.getId();
        } catch (Exception ex) {
            return "-1";
        }
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(getCurrentUserId());
    }

}

