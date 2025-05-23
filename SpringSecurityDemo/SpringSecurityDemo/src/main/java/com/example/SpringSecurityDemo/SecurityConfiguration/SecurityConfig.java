package com.example.SpringSecurityDemo.SecurityConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
     return   http
             .csrf(csrf-> csrf.disable())
        .authorizeHttpRequests((req )->
                     req.anyRequest().authenticated())
             .sessionManagement(session ->
                     session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
             .formLogin(withDefaults())
                .httpBasic(withDefaults())
             .build();
    }

//
//    @Bean
//    // using InMemoryUserDetailsManager
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withUsername("user1")
//                .password("{noop}pass1")
//                .roles("USER")
//                .build();
//
//        UserDetails admin= User.withUsername("admin")
//                .password( "{noop}admin")
//                .roles("ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1,admin);
//    }
    //Role based Authentication using database




}
