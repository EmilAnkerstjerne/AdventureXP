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
import java.time.LocalDate;
import java.time.LocalTime;
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
        - start&end = 15%=0
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

        if (!checkTimes(reservation.getStartTime(), reservation.getEndTime(), activityId)){
            return null;
        }

        List<Reservation> reservations = reservationRepository.getActivityReservationsForDay(activityId, reservation.getDate()); //Removed to string
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

    public boolean checkDate(LocalDate date){
        LocalDate now = LocalDate.now();

        String today = now.toString();

        String limit = now.plusDays(bookableDays).toString();

        if (date.toString().compareTo(today) < 0){
            return false; //If requested is before today
        }

        if (date.toString().compareTo(limit) > 0){
            return false; //If requested is after limit
        }

        return true;
    }

    public boolean checkTimes(LocalTime start, LocalTime end, int activityId){
        if (start.toString().compareTo(end.toString()) > 0){
            return false;
        }
        String[] startTimeArr = start.toString().split(":");
        String[] endTimeArr = end.toString().split(":");

        /*
        if (!startTimeArr[2].equals("00") || !endTimeArr.equals("00")){
            return false;
        }
         */

        float startTime = (Integer.parseInt(startTimeArr[0]) + (Integer.parseInt(startTimeArr[1])) / 60f);
        float endTime = (Integer.parseInt(endTimeArr[0]) + (Integer.parseInt(endTimeArr[1])) / 60f);

        if (startTime < openingTime){
            return false;
        }

        if (endTime > closingTime){
            return false;
        }


        int startMin = Integer.parseInt(startTimeArr[1]);
        int endMin = Integer.parseInt(endTimeArr[1]);

        if (startMin%15 > 0 || endMin%15 > 0){
            return false;
        }

        double minDuration = activityRepository.findById(activityId).get().getMinDurationHours();

        if (minDuration > (endTime - startTime)){
            return false;
        }

        return true;
    }

    public boolean checkAvailability(List<Reservation> reservations, Reservation reservation){

        for (Reservation r : reservations){
            String newStart = reservation.getStartTime().toString();
            String newEnd = reservation.getEndTime().toString();

            String oldStart = r.getStartTime().toString();
            String oldEnd = r.getEndTime().toString();

            if (newStart.compareTo(oldStart) >= 0 && newStart.compareTo(oldEnd) <= 0){
                return false;
            }

            if (newEnd.compareTo(oldStart) >= 0 && newEnd.compareTo(oldEnd) <= 0){
                return false;
            }

            if (newStart.compareTo(oldStart) <= 0 && newEnd.compareTo(oldEnd) >= 0){
                return false;
            }
        }

        return true;
    }

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

    public Reservation editReservation(Reservation reservation, Principal principal) {
        //Real reservation
        //User-reservation
        //Legal
        //Available - Not current

        if (reservation == null ||
                reservation.getStartTime() == null ||
                reservation.getEndTime() == null ||
                reservation.getDate() == null) {
            return null;
        }

        User user = userRepository.findByUsername(principal.getName()).get();

        Reservation currentReservation = reservationRepository.findById(reservation.getId()).get();

        if (!(user==currentReservation.getUser())){
            return null;
        }

        if (!checkDate(reservation.getDate())){
            return null;
        }

        if (!checkTimes(reservation.getStartTime(), reservation.getEndTime(), currentReservation.getActivity().getId())){
            return null;
        }

        List<Reservation> reservations = reservationRepository.getActivityReservationsForDay(currentReservation.getActivity().getId(), reservation.getDate()); //Removed to string
        reservations.remove(currentReservation);

        if (!checkAvailability(reservations, reservation)){
            return null;
        }

        currentReservation.setDate(reservation.getDate());
        currentReservation.setStartTime(reservation.getStartTime());
        currentReservation.setEndTime(reservation.getEndTime());

        reservationRepository.save(currentReservation);
        return currentReservation;
    }
}
