package com.example.demo.controller;

import com.example.demo.model.StoreItem;
import com.example.demo.repository.StoreItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StoreItemController {

    private final StoreItemRepository storeItemRepository;

    public StoreItemController(StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;
    }


    @GetMapping("/store")
    public String StoreItem(Model model){
        //StoreItem cola = new StoreItem("cola",20.95);
        //storeItemRepository.save(cola);
        model.addAttribute("storeitems", storeItemRepository.findAll());

        return "StoreItemTemp";
    }



}

