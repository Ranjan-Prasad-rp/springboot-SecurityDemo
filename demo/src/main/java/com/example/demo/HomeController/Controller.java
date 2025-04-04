package com.example.demo.HomeController;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/test")
    public String test(){
        return "controller is working";
    }

    @GetMapping("/user")
    public String user(){
        return "user is working";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin is working";
    }




}
