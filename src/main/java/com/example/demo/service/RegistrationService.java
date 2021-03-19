package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService implements RegistrationServiceInt{

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(User user){
        if (user == null){
            return null;
        }
        else if (usernameTaken(user.getUsername())){
            return null;
        }

        user.setRoles("ROLE_USER");
        user.setActive(true);
        user.setId(0);
        //user.setReservations(new ArrayList<Reservation>());

        return userRepository.save(user);
    }

    public boolean usernameTaken(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            return true;
        }else {
            return false;
        }
    }
}
