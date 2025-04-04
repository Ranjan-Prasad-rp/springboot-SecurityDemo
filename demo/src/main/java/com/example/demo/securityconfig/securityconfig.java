package com.example.demo.securityconfig;

import com.example.demo.service.userdetailservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class securityconfig {
    @Autowired
     private userdetailservice userdetailservice;   // user your custom user detail service class
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  // it is a filter which delgates the request to the Authentication Manager
        http
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(reuest -> reuest
                .requestMatchers("/user").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()
        );
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {       // type of password hasing done for password security
        return new BCryptPasswordEncoder();
    }



    @Bean // after this you can use repo for all authentication and authorization if role is given in security filter
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws Exception{
        return config.getAuthenticationManager();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {    //  Authentication Manager uses Authentication provider for the Validation (username and password )
//        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        builder.userDetailsService(userdetailservice)
//                .passwordEncoder(passwordEncoder());
//        return builder.build();
//
//
//    }
}

//    @Bean
//  public AuthenticationProvider authenticationProvider( PasswordEncoder passwordEncoder) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(userdetailservice);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        return daoAuthenticationProvider;
//    }
//}
