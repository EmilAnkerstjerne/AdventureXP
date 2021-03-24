package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkScheduleController {

    @GetMapping("/work-schedule")
    public String workSchedule(){
        return "work-schedule";
    }
}
