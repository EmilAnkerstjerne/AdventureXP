package com.example.demo.controller;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.service.BookingServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class BookingRestController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    BookingServiceInt bookingServiceInt;

    @GetMapping("/booking/reservation/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable int id){
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

    @PostMapping(value = "/booking/reserve/{activityId}", consumes = "application/json")
    public ResponseEntity<Reservation> reserve(@PathVariable int activityId, @RequestBody Reservation reservation, Principal principal){
        Reservation newReservation = bookingServiceInt.newReservation(reservation, principal, activityId);
        if (newReservation == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
        }
    }

    @GetMapping("/booking/myReservations")
    public List<Reservation> myReservations(Principal principal){
        List<Reservation> list = bookingServiceInt.getUsersCurrentReservations(principal);
        return list;
    }

    @PutMapping("/booking/edit")
    public ResponseEntity<Reservation> editReservation(@RequestBody Reservation reservation, Principal principal){
        Reservation realReservation = bookingServiceInt.editReservation(reservation, principal);
        if (realReservation == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(realReservation, HttpStatus.OK);
        }
    }

    @DeleteMapping("/booking/delete/{id}")
    public ResponseEntity<Integer> deleteReservation(@PathVariable int id, Principal principal){

        boolean deleted = bookingServiceInt.deleteById(principal, id);

        if (deleted){
            return new ResponseEntity<Integer>( id, HttpStatus.OK);
        }else {
            return new ResponseEntity<Integer>(0, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/booking/reservation/day/{date}/{activityId}")
    public List<Reservation> getReservationByDay(@PathVariable String date, @PathVariable int activityId){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate newDate = LocalDate.parse(date);
        return reservationRepository.getActivityReservationsForDay(activityId, newDate);
    }

    @GetMapping("/test69/{year}/{month}/{id}")
    public List<Reservation> test(@PathVariable int year, @PathVariable int month, @PathVariable int id){
        return reservationRepository.getReservationByActivityIdAndMonthYear(year, month, id);
    }
}
