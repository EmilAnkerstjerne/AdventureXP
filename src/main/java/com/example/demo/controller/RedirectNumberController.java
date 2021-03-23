package com.example.demo.controller;

import com.example.demo.service.RedirectList;
import com.example.demo.service.RedirectNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scopedproxy")
public class RedirectNumberController {

    @Autowired
    private RedirectList numbers;

    @GetMapping("/form")
    public String showForm(Model model){
        if (!numbers.isEmpty()) {
            return numbers.peekLast().getNumber();
        } else {
            return null;
        }
    }

    @GetMapping("/form/{id}")
    public String putForm(@PathVariable String id, Model model){
        numbers.add(new RedirectNumber(id));
        String number = numbers.peekLast().getNumber();

        return number;
    }
}
