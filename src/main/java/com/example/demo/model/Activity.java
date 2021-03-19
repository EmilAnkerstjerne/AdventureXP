package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
//Helping with identifying via id?
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//Potentially still responsible for fixing lazy error
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    /* Annotations that might have relevance
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonBackReference
    @JsonManagedReference
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
     */
    //Used to make relation to Reservation
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@ActivityReservation")
    //Used to get objects as id in json
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activity")
    private List<Reservation> reservations;

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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxParticipants=" + maxParticipants +
                ", description='" + description + '\'' +
                ", minDurationHours=" + minDurationHours +
                '}';
    }
}
