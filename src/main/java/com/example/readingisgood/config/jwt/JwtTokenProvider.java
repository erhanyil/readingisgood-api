package com.example.readingisgood.config.jwt;

import com.example.readingisgood.config.AppConfig;
import com.example.readingisgood.constant.Role;
import com.example.readingisgood.dto.token.TokenDto;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.service.AuthService;
import com.example.readingisgood.service.impl.AuthServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Autowired
    AppConfig appConfig;

    @Autowired
    @Qualifier(value = AuthServiceImpl.NAME)
    private AuthService userService;

    public TokenDto createToken(String id, String email) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", Role.USER);
        claims.put("id", id);

        Date now = new Date();
        TokenDto tokenDto = new TokenDto();
        Date validity = new Date(now.getTime() + appConfig.getApi().getValidity());
        JwtBuilder jwtBuilder = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .signWith(SignatureAlgorithm.HS512, appConfig.getApi().getSecretKey());
        tokenDto.setExpire(validity);
        tokenDto.setToken(jwtBuilder.compact());
        return tokenDto;
    }

    public Authentication getAuthentication(String token) {
        Customer baseUserEntity = this.userService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(baseUserEntity, "", baseUserEntity.getAuthorities());
    }

    public String getEmail(String token) {
        return Jwts.parser().setSigningKey(appConfig.getApi().getSecretKey()).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(appConfig.getApi().getSecretKey()).parseClaimsJws(token);
            Date expirationDate = claims.getBody().getExpiration();
            if (expirationDate != null) {
                return !expirationDate.before(new Date());
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}