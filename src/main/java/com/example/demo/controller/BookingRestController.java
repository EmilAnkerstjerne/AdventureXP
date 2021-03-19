package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.BookingServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class BookingRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookingServiceInt bookingServiceInt;

    @GetMapping("/booking/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id, Principal principal){
        System.out.println(principal.getName());
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()){
            Reservation realReservation = reservation.get();
            return new ResponseEntity<>(realReservation, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/booking/user/{id}")
    public List<Reservation> userReservations(@PathVariable int id){
        return reservationRepository.getUsersReservations(id);
    }

    @PostMapping(value = "/booking/reserve/{id}", consumes = "application/json")
    public ResponseEntity<Reservation> reserve(@PathVariable int id, @RequestBody Reservation reservation){
        return null;
    }

    @GetMapping("/booking/myReservations")
    public List<Reservation> myReservations(Principal principal){
        return null;
    }

    @PutMapping("/booking/edit")
    public ResponseEntity<Reservation> editReservation(@RequestBody Reservation reservation, Principal principal){
        //Ended?
        return null;
    }

    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<Integer> deleteReservation(@PathVariable int id, Principal principal){
        return null;
    }

}
