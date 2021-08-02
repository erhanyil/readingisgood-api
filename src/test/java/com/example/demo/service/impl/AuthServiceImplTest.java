package com.example.demo.service.impl;

import com.example.readingisgood.config.jwt.JwtTokenProvider;
import com.example.readingisgood.dto.token.CreateTokenDto;
import com.example.readingisgood.dto.token.TokenDto;
import com.example.readingisgood.handler.FriendlyException;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import com.example.readingisgood.service.impl.AuthServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @InjectMocks
    AuthServiceImpl authService;

    @Test(expected = NullPointerException.class)
    public void loadUserByUsername_return_null_customer() {
        when(customerRepository.findByEmail(anyString())).thenReturn(null);
        authService.loadUserByUsername("erhan_yil@windowslive.com");
    }

    @Test
    public void loadUserByUsername_return_customer_with_disable() {
        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        Customer result = authService.loadUserByUsername("erhan_yil@windowslive.com");
        Assert.assertEquals(result, customer);
    }

    @Test
    public void loadUserByUsername_return_customer_with_enable() {
        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        Customer result = authService.loadUserByUsername("erhan_yil@windowslive.com");
        Assert.assertEquals(result, customer);
    }

    @Test(expected = RuntimeException.class)
    public void createToken_with_not_exist_account() {
        CreateTokenDto createTokenDto = new CreateTokenDto();
        createTokenDto.setEmail("erhan_yil@windowslive.com");
        createTokenDto.setPassword("");
        when(customerRepository.findByEmail(anyString())).thenReturn(null);
        authService.createToken(createTokenDto);
    }

    @Test
    public void createToken_with_exist_account() {
        CreateTokenDto createTokenDto = new CreateTokenDto();
        createTokenDto.setEmail("erhan_yil@windowslive.com");
        createTokenDto.setPassword("");

        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("");
        tokenDto.setExpire(new Date());

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), createTokenDto.getPassword()))).thenReturn(new UsernamePasswordAuthenticationToken("",""));
        when(jwtTokenProvider.createToken(customer.getId(), customer.getEmail())).thenReturn(tokenDto);
        TokenDto result = authService.createToken(createTokenDto);
        Assert.assertEquals(result, tokenDto);
    }

    @Test(expected = FriendlyException.class)
    public void createToken_with_exist_and_not_valid_account() {
        CreateTokenDto createTokenDto = new CreateTokenDto();
        createTokenDto.setEmail("erhan_yil@windowslive.com");
        createTokenDto.setPassword("");

        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("");
        tokenDto.setExpire(new Date());

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), createTokenDto.getPassword()))).thenThrow(new FriendlyException(""));
        when(jwtTokenProvider.createToken(customer.getId(), customer.getEmail())).thenReturn(tokenDto);
        authService.createToken(createTokenDto);
    }

    @Test(expected = FriendlyException.class)
    public void createToken_with_exist_and_not_valid_account_null() {
        CreateTokenDto createTokenDto = new CreateTokenDto();
        createTokenDto.setEmail("erhan_yil@windowslive.com");
        createTokenDto.setPassword("");

        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("");
        tokenDto.setExpire(new Date());

        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(customer.getUsername(), createTokenDto.getPassword()))).thenThrow(new NullPointerException());
        when(jwtTokenProvider.createToken(customer.getId(), customer.getEmail())).thenReturn(tokenDto);
        authService.createToken(createTokenDto);
    }

    @Test(expected = RuntimeException.class)
    public void credential_with_not_exist() {
        when(customerRepository.findByEmail(anyString())).thenReturn(null);
        authService.credential();
    }

    @Test(expected = NullPointerException.class)
    public void credential() {
        Customer customer = new Customer();
        customer.setEmail("erhan_yil@windowslive.com");
        customer.setPassword("");
        customer.setFirstName("Erhan");
        customer.setLastName("Yıldırım");
        customer.setId("1");

        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        Customer result = authService.credential();
        Assert.assertEquals(result, customer);
    }
}