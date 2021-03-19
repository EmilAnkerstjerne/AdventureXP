package com.example.demo.controller;

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


    @GetMapping("/store/manage")
    public String StoreItem(Model model){
        model.addAttribute("storeitems", storeItemRepository.findAll());

        return "storeItemTemp";
    }

    @GetMapping("/store")
    public String store(Model model){
        model.addAttribute("storeitems", storeItemRepository.findAll());
        return "store";
    }


}

