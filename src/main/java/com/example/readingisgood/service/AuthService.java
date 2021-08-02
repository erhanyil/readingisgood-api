package com.example.readingisgood.service;

import com.example.readingisgood.dto.token.CreateTokenDto;
import com.example.readingisgood.dto.token.TokenDto;
import com.example.readingisgood.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {

    TokenDto createToken(CreateTokenDto createTokenDto);

    Customer credential();

    Customer loadUserByUsername(String mobile);
}
