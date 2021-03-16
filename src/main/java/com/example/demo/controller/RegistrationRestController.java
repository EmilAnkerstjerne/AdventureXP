package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RegistrationServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegistrationServiceInt registrationService;

    @PostMapping(value = "/registration/form", consumes = "application/json")
    public ResponseEntity<User> registrationForm(@RequestBody User user){
        User newUser = registrationService.registerNewUser(user);
        System.out.println(user);
        if (newUser == null){
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }else {
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }
}
