package com.example.SpringSecurityDemo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("/Home")
    public String Home( HttpServletRequest http){


        return "Welcome to Home Page :"+ http.getSession().getId();
    }
}
