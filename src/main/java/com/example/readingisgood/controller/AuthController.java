package com.example.readingisgood.controller;

import com.example.readingisgood.dto.token.CreateTokenDto;
import com.example.readingisgood.dto.token.TokenDto;
import com.example.readingisgood.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/create_token")
    public TokenDto createToken(@RequestBody CreateTokenDto createTokenDto) {
        return this.authService.createToken(createTokenDto);
    }
}
