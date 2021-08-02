package com.example.readingisgood.config;


import com.example.readingisgood.config.jwt.JwtSecurityConfigurer;
import com.example.readingisgood.config.jwt.JwtTokenProvider;
import com.example.readingisgood.constant.Role;
import com.example.readingisgood.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    @Configuration
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        JwtTokenProvider jwtTokenProvider;

        @Autowired
        @Qualifier(value = AuthServiceImpl.NAME)
        private UserDetailsService userService;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private AppConfig appConfig;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security",
                    "/swagger-ui.html", "/webjars/**", "/v2/swagger.json", "/h2-console");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic().disable()
                    .csrf().disable()
                    .cors().configurationSource(corsConfigurationSource()).and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and().authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/customer").permitAll()//
                    .antMatchers("/auth/create_token", "/h2-console/**").permitAll()//
                    .antMatchers("/**").hasRole(Role.USER)//
                    .anyRequest().authenticated()
                    .and().csrf().disable()
                    .anonymous().authorities(Role.ROLE_ANONYMOUS)
                    .and().headers().frameOptions().disable()
                    .and().apply(new JwtSecurityConfigurer(jwtTokenProvider));
        }

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
        }

        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            final CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList(appConfig.getApi().getAllowedOrigin()));
            configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "OPTIONS"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept", "x-client-key", "x-client-token", "x-client-secret", "Authorization"));
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

    }

}