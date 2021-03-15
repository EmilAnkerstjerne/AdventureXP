package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/home")
    public String home(){
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/test02")
    public String test(){
        return ("<h1>Test</h1>");
    }

    @GetMapping("/user/1")
    public String user1(){
        return ("<h1>Welcome User1</h1>");
    }

    @GetMapping("/user/2")
    public String user2(){
        return ("<h1>Welcome User2</h1>");
    }
}
