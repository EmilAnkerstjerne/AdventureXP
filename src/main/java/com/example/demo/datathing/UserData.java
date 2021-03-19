package com.example.demo.datathing;

import com.example.demo.model.Activity;
import com.example.demo.model.Reservation;
import com.example.demo.model.User;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;

//TODO: Adds data
@Component
public class UserData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public void run(String... args) throws Exception {

        //----------Test data for users----------
        User user1 = new User();
        user1.setUsername("Admin");
        user1.setPassword("root");
        user1.setActive(true);
        user1.setRoles("ROLE_ADMIN");
        user1.setName("Gert");
        user1.setPhoneNumber("69696969");

        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Kaj");
        user2.setPassword("Haj");
        user2.setActive(true);
        user2.setRoles("ROLE_USER");
        user2.setName("Kaj Johanssen");
        user2.setPhoneNumber("42424242");

        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("A");
        user3.setPassword("A");
        user3.setActive(true);
        user3.setRoles("ROLE_USER");
        user3.setName("Super ABE");
        user3.setPhoneNumber("12345678");

        userRepository.save(user3);


        //----------Test data for activities----------
        Activity act1 = new Activity("Paintball", 30, 1, "I paintball deles I op i to eller flere hold og spiller smaa runder af 10 minutter mod hinanden. Der betales 1kr pr. kugle I bruger, samt leje af bane inkl. instruktoer paa hhv 150kr/t og 300kr/t for den lille og store bane.");
        Activity act2 = new Activity("Gokart", 10, 1, "I koerer maks 10 ad gangen paa banen, og der laves en faelles scoretavle, som holder styr over alle positioner. Der skal betales 200kr/t pr person.");
        Activity act3 = new Activity("Sumo Wrestling", 2, 0.5, "Wrestling Wrestling Wrestling Wrestling Wrestling Wrestling Wrestling ");
        Activity act4 = new Activity("Menneskelig bordfodbold", 10, 1, "Man bliver spaendt fast og sat paa hvad der ligner en stor bordfodboldbane, og saa puster man til bolden og proever at score.");
        activityRepository.save(act1);
        activityRepository.save(act2);
        activityRepository.save(act3);
        activityRepository.save(act4);

        //----------Test data for reservations----------
        Reservation res1 = new Reservation(
                new Date(1616540400000l),
                new Time(46800000),
                new Time(54000000),
                activityRepository.findById(1).get(),
                userRepository.findById(2).get()
        );

        Reservation res2 = new Reservation(
                new Date(1616454000000l),
                new Time(46800000),
                new Time(54000000),
                activityRepository.findById(1).get(),
                userRepository.findById(2).get()
        );

        Reservation res3 = new Reservation(
                new Date(1616540400000l),
                new Time(32400000),
                new Time(39600000),
                activityRepository.findById(1).get(),
                userRepository.findById(3).get()
        );

        Reservation res4 = new Reservation(
                new Date(1616626800000l),
                new Time(28800000),
                new Time(32400000),
                activityRepository.findById(2).get(),
                userRepository.findById(3).get()
        );

        Reservation res5 = new Reservation(
                new Date(1616626800000l),
                new Time(43200000),
                new Time(54000000),
                activityRepository.findById(3).get(),
                userRepository.findById(2).get()
        );

        reservationRepository.save(res1);
        reservationRepository.save(res2);
        reservationRepository.save(res3);
        reservationRepository.save(res4);
        reservationRepository.save(res5);


        /*
        Date date = new Date(1616626800000l);

        Time time = new Time(43200000);

        System.out.println(date);
        System.out.println(time);

        String d = date.toString();

        String t = time.toString();

        System.out.println(d + " " + t);
        //date.

         */
    }
}
