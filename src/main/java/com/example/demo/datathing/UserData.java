package com.example.demo.datathing;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

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
    }
}
