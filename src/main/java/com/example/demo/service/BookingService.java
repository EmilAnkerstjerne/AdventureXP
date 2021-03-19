package com.example.demo.service;

import com.example.demo.model.Activity;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService implements BookingServiceInt{
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    UserRepository userRepository;

    private int openingTime = 8;
    private int closingTime = 20;
    private int bookableDays = 30;


    public Reservation newReservation(Reservation reservation, Principal principal, int activityId){
        /*Checklist:
        - Null
        - Activity=Active
        - start<End && Opening hours
        - start&end = 15%==0
        - MinDuration
        - Date = allowed
        -
        - Compare to res of act on day - Time comparison
         */
        if (reservation == null ||
                reservation.getDate() == null ||
                reservation.getEndTime() == null ||
                reservation.getStartTime() == null) {
            return null;
        }

        if (!checkDate(reservation.getDate())){
            return null;
        }

        if (!checkActivity(activityId)){
            return null;
        }

        if (!checkTimes(reservation.getStartTime(), reservation.getEndTime())){
            return null;
        }

        List<Reservation> reservations = reservationRepository.getActivityReservationsForDay(activityId, reservation.getDate().toString());
        if (!checkAvailability(reservations, reservation)){
            return null;
        }

        User user = userRepository.findByUsername(principal.getName()).get();
        Activity activity = activityRepository.findById(activityId).get();
        reservation.setActivity(activity);
        reservation.setUser(user);

        reservationRepository.save(reservation);
        return reservation;
    }

    public boolean checkActivity(int activityId){
        Optional<Activity> activity = activityRepository.findById(activityId);
        return activity.isPresent();
    }

    public boolean checkDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        String today = sdf.format(cal.getTime());

        cal.add(Calendar.DAY_OF_MONTH, bookableDays);

        String limit = sdf.format(cal);

        if (date.toString().compareTo(today) < 0){
            return false; //If requested is before today
        }

        if (date.toString().compareTo(limit) > 0){
            return false; //If requested is after limit
        }

        return true;
    }

    public boolean checkTimes(Time start, Time end){
        if (start.toString().compareTo(end.toString()) > 0){
            return false;
        }


        return true;
    }

    public boolean checkAvailability(List<Reservation> reservations, Reservation reservation){
        return false;
    }


    //public boolean

    public List<Reservation> getUsersCurrentReservations(Principal principal){
        if (principal == null){
            return null;
        }
        User user = userRepository.findByUsername(principal.getName()).get();
        return reservationRepository.getUsersCurrentReservations(user.getId());
    }

    public boolean deleteById(Principal principal, int reservationId){
        int userId = userRepository.findByUsername(principal.getName()).get().getId();

        Reservation reservation = reservationRepository.findById(reservationId).get();

        if (reservation.getUser().getId() == userId){
            reservationRepository.delete(reservation);
            return true;
        }else {
            return false;
        }
    }

}
