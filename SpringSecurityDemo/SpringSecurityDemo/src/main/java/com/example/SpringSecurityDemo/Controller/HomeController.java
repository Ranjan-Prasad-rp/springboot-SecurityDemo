package com.example.SpringSecurityDemo.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {
    @GetMapping("/Home")
    public String Home( HttpServletRequest http){
        return "Welcome to Home Page :"+ http.getSession().getId() ;
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
  public String user(){
        return "hello i am user";
  }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "hello i am admin";
    }

}
