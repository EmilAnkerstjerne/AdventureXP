package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {

    @GetMapping("/booking")
    public String booking(){
        return "booking";
    }

    @GetMapping("/bookings")
    public String bookings(){
        return "bookings";
    }
}
