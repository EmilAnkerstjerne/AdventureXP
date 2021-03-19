package com.example.demo.service;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;

@Service
public class BookingService implements BookingServiceInt{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ActivityRepository activityRepository;

    /*
    public Reservation newReservation(Reservation reservation, Principal principal, int activityId){
        if (reservation == null){
            return null;
        }
    }

    public boolean checkDate(Date date){

    }

    public boolean


     */
}
