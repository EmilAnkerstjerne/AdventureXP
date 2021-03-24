package com.example.demo.datathing;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

//TODO: Adds data
@Component
public class UserData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

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
                LocalDate.of(2021, 3, 24),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                activityRepository.findById(1).get(),
                userRepository.findById(2).get()
        );

        Reservation res2 = new Reservation(
                LocalDate.of(2021, 3, 23),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                activityRepository.findById(1).get(),
                userRepository.findById(2).get()
        );

        Reservation res3 = new Reservation(
                LocalDate.of(2021, 3, 24),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                activityRepository.findById(1).get(),
                userRepository.findById(3).get()
        );

        Reservation res4 = new Reservation(
                LocalDate.of(2021, 3, 25),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                activityRepository.findById(2).get(),
                userRepository.findById(3).get()
        );

        Reservation res5 = new Reservation(
                LocalDate.of(2021, 3, 25),
                LocalTime.of(13, 0),
                LocalTime.of(16, 0),
                activityRepository.findById(3).get(),
                userRepository.findById(2).get()
        );

        reservationRepository.save(res1);
        reservationRepository.save(res2);
        reservationRepository.save(res3);
        reservationRepository.save(res4);
        reservationRepository.save(res5);

        //----------Test data for instructors----------

        Instructor i1 = new Instructor("Gert");
        Instructor i2 = new Instructor("Bob");
        Instructor i3 = new Instructor("Ib");
        Instructor i4 = new Instructor("Billy");
        Instructor i5 = new Instructor("Margrethe");
        Instructor i6 = new Instructor("Bøje");

        instructorRepository.save(i1);
        instructorRepository.save(i2);
        instructorRepository.save(i3);
        instructorRepository.save(i4);
        instructorRepository.save(i5);
        instructorRepository.save(i6);

        //----------Test data for assignments----------

        Assignment a1 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 03, 1));
        Assignment a2 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 04, 2));
        Assignment a3 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 03, 3));
        Assignment a4 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 03, 4));
        Assignment a5 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 03, 5));
        Assignment a6 = new Assignment(instructorRepository.findById(1).get(), LocalDate.of(2021, 03, 6));
        Assignment a7 = new Assignment(instructorRepository.findById(2).get(), LocalDate.of(2021, 03, 8));

        assignmentRepository.save(a1);
        assignmentRepository.save(a2);
        assignmentRepository.save(a3);
        assignmentRepository.save(a4);
        assignmentRepository.save(a5);
        assignmentRepository.save(a6);
        assignmentRepository.save(a7);
    }
}
