package com.example.demo.repository;

import com.example.demo.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    @Query("SELECT a FROM Assignment a WHERE YEAR(date) = ?1 AND MONTH(date) = ?2")
    List<Assignment> getAssignmentsByYearMonth(int year, int month);
}
