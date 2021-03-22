package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

/* Annotations that might have some relevance
    @JsonManagedReference
    @JsonBackReference
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
 */
    //Used to get objects as id in json
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty("activity_id")
    //Sets foreign key and references Activity (Also^)
    @JoinColumn(name = "activity_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_reservation_activity"))
    private Activity activity;

/* Annotations that might have some relevance
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    @JsonBackReference
 */
    //Sets foreign key and references User
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_reservation_user"))
    @JsonProperty("user_id")
    //Used to get objects as id in json (Also^)
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Reservation() {
    }

    public Reservation(LocalDate date, LocalTime startTime, LocalTime endTime, Activity activity, User user) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activity = activity;
        this.user = user;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                //", activity=" + activity +
                //", user=" + user +
                '}';
    }
}
