package com.example.demo.service;

import com.example.demo.model.Reservation;

import java.security.Principal;
import java.util.List;

public interface BookingServiceInt {

    public List<Reservation> getUsersCurrentReservations(Principal principal);

    public boolean deleteById(Principal principal, int reservationId);

    public Reservation newReservation(Reservation reservation, Principal principal, int activityId);

    public Reservation editReservation(Reservation reservation, Principal principal);
}
