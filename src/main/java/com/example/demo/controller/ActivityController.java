package com.example.demo.controller;

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ActivityController {
    @Autowired
    ActivityRepository activityRepository;


    @GetMapping("/index")
    public String test(){
        return "index";
    }

    @GetMapping("/activities")
    public String Activity(Model model){
//        model.addAttribute("activities", activityRepository.findAll());
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

}
