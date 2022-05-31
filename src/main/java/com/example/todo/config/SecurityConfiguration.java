package com.example.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests()
                .antMatchers("/actuator/**")
                    .permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/rapidoc.html")
                    .permitAll()
                .antMatchers("/favicon.ico", "/h2-console/**")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                .headers()
                    .frameOptions().sameOrigin()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
                .csrf()
                    .disable()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt())
                .build();
    }
}
