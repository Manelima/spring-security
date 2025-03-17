package com.example.spring_security_api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("managers").hasAnyRole("MANAGERS")
                        .requestMatchers("/users").hasAnyRole("USER","MANAGERS")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults -> {})
                .httpBasic(withDefaults -> {});

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}user123")
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .password("{noop}master")
                        .roles("MANAGERS")
                        .build()
        );
    }
}
