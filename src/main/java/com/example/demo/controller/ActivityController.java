package com.example.demo.controller;

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ActivityController {
    @Autowired
    ActivityRepository activityRepository;


    @GetMapping("/index")
    public String test(){
        return "index";
    }

    @GetMapping("/activities")
    public String Activity(){
        return "activities";
    }

    @GetMapping("/activities/gallery")
    public String gallery(){
        return "activity_gallery";
    }

    @GetMapping("/activities/create")
    public String createActivity(){
        return "create_activities";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/activities/gallery";
    }
}
