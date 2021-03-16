package com.example.demo.datathing;

import com.example.demo.model.Activity;
import com.example.demo.model.User;
import com.example.demo.repository.ActivityRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//TODO: Adds data
@Component
public class UserData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setUsername("Admin");
        user1.setPassword("root");
        user1.setActive(true);
        user1.setRoles("ROLE_ADMIN");

        userRepository.save(user1);

        User user2 = new User();
        user2.setUsername("Kaj");
        user2.setPassword("Haj");
        user2.setActive(true);
        user2.setRoles("ROLE_USER");

        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("A");
        user3.setPassword("A");
        user3.setActive(true);
        user3.setRoles("ROLE_USER");

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
    }
}
