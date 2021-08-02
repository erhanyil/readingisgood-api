package com.example.readingisgood.service.impl;

import com.example.readingisgood.config.jwt.JwtTokenProvider;
import com.example.readingisgood.constant.GenericError;
import com.example.readingisgood.dto.token.CreateTokenDto;
import com.example.readingisgood.dto.token.TokenDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service(value = AuthServiceImpl.NAME)
public class AuthServiceImpl implements AuthService {



    public static final String NAME = "AuthService";

    private final AuthenticationManager authenticationManager;

    private final CustomerRepository customerRepository;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, CustomerRepository customerRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.customerRepository = customerRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public Customer loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(email).orElse(null);
        if (customer == null)
            return null;
        if (customer.isEnabled()) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            for (GrantedAuthority role : customer.getAuthorities()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
            }
            return customer;
        }
        throw new FriendlyException(GenericError.INACTIVE_USER);
    }

    @Override
    public TokenDto createToken(CreateTokenDto createTokenDto) {
        Customer customer = customerRepository.findByEmail(createTokenDto.getEmail()).orElseThrow(() -> new FriendlyException("ACCOUNT_NOT_FOUNT"));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), createTokenDto.getPassword()));
        } catch (Exception e) {
            if (e.getCause() instanceof FriendlyException) {
                throw new FriendlyException(e.getCause().getMessage());
            }
            throw new FriendlyException(GenericError.CREDENTIAL_WRONG);
        }
        return jwtTokenProvider.createToken(customer.getId(), customer.getUsername());
    }

    @Override
    public Customer credential() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        return customerRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new FriendlyException(GenericError.RECORD_NOT_FOUND));
    }

}
