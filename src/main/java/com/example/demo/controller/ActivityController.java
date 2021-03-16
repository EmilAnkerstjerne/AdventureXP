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
        Activity act = new Activity("Paintball", 10, 10, "Kom og spil paintball");
        activityRepository.save(act);
        return "index";
    }

    @GetMapping("/activities")
    public String Activity(Model model){
        model.addAttribute("activities", activityRepository.findAll());
        return "activities";
    }


}
