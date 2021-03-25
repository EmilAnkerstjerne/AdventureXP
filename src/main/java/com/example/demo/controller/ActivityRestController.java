package com.example.demo.controller;

import com.example.demo.model.Activity;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
public class ActivityRestController {
    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    @DeleteMapping("/activities/deleteActivity/{id}")
    public RedirectView deleteActivity(@PathVariable int id){
        reservationRepository.deleteAllByActivity_Id(id);
        activityRepository.delete(activityRepository.getOne(id));
        return new RedirectView("/activities");
    }

    @GetMapping("/activities/{id}")
    public Activity getActivityById(@PathVariable int id){
        return activityRepository.getOne(id);
    }

    @PostMapping(value = "/activities/create/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Activity postActivity(@RequestBody Activity activity){
        return activityRepository.save(activity);
    }

    @GetMapping("/activities/all")
    public List<Activity> getAllActivities(){
        return activityRepository.findAll();
    }

    @GetMapping("/activities/all/noReservations")
    public List<Activity> getAllActivitiesWithoutReservations(){
        List<Activity> list = activityRepository.findAll();
        for(Activity a : list){
            a.setReservations(null);
        }
        return list;
    }
}
