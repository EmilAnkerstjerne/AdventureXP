package com.example.demo.controller;

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class ActivityRestController {
    @Autowired
    ActivityRepository activityRepository;


    @GetMapping("/deleteActivity")
    public RedirectView deleteActivity(@RequestParam int id){
        activityRepository.delete(activityRepository.getOne(id));
        return new RedirectView("/activities");
    }

    @GetMapping("/activities/all")
    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

}
