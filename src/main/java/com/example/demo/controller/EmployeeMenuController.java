package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeMenuController {

    @GetMapping("/emenu")
    public String mainMenu(){
        return "employee-menu";
    }
}
