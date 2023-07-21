package com.example.springsecuritytest.config;

import com.example.springsecuritytest.service.PersonService;
import com.example.springsecuritytest.service.PersonServiceImpl;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final PersonService personService;
    private final AuthenticationHandlerCustom authenticationHandlerCustom;

    public SecurityConfig(PersonService personService, AuthenticationHandlerCustom authenticationHandlerCustom) {
        this.personService = personService;
        this.authenticationHandlerCustom = authenticationHandlerCustom;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/admin_page/**").hasRole("ADMIN")
                        .requestMatchers("/persons/**").hasAnyRole("MANAGER", "ADMIN")
                        .requestMatchers("/products/**").hasAnyRole("CUSTOMER", "MANAGER", "ADMIN")
                        .requestMatchers("/**").authenticated()
                        .requestMatchers("/").permitAll()
                )
                .formLogin((form) -> form
                        .defaultSuccessUrl("/")
                        .failureForwardUrl("/login")
                        .successHandler(authenticationHandlerCustom)
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/")
                        .permitAll());

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(personService);
        return authenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
