package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationGetController {

    @GetMapping("/registration/register")
    public String registrationPage(){
        return "registration";
    }

    @GetMapping("/registration/registration_style.css")
    public String regStylesheet(){
        return "../static/registration_style";
    }
}
