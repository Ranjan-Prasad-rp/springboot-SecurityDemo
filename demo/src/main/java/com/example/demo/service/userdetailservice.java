package com.example.demo.service;


import com.example.demo.entity.user;
import com.example.demo.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class userdetailservice implements UserDetailsService {

    @Autowired
    private userRepo UserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         user user  = UserRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));

        // Assuming your User entity has getPassword() and getRole() methods


         return new User(user.getUsername(),
                 user.getPassword(),
                 true,
                 true,
                 true,
                 true,
                 user.getRoles()
                         .stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet() )
                 );







    }
}
