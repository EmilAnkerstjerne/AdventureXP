package com.example.demo.repository;

import com.example.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.user.id = ?1")
    List<Reservation> getUsersReservations(int userId);

    @Query("SELECT r FROM Reservation r WHERE r.user.id = ?1 AND r.date >= CURDATE()")
    List<Reservation> getUsersCurrentReservations(int userId);

    @Query("SELECT r FROM Reservation r WHERE r.activity.id = ?1 AND r.date = ?2")
    List<Reservation> getActivityReservationsForDay(int activityId, LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE YEAR(r.date) = ?1 AND MONTH(r.date) = ?2 AND r.activity.id = ?3")
    List<Reservation> getReservationByActivityIdAndMonthYear(int year, int month, int activityId);
}
