package com.example.demo.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;
    @NotNull
    private int maxParticipants;
    @NotNull
    private String description;
    @NotNull
    private double minDurationHours;

    public Activity() {
    }

    public Activity(String name, int maxParticipants, double minDurationHours, String description) {
        this.name = name;
        this.maxParticipants = maxParticipants;
        this.description = description;
        this.minDurationHours = minDurationHours;
    }

    public int getId() {
        return id;
    }

    public double getMinDurationHours() {
        return minDurationHours;
    }

    public void setMinDurationHours(double minDurationHours) {
        this.minDurationHours = minDurationHours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
