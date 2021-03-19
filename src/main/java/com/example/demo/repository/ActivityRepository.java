package com.example.demo.repository;

import com.example.demo.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    //@Query("")

}
