package com.example.demo.controller;

import com.example.demo.model.StoreItem;
import com.example.demo.repository.StoreItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class StoreItemRestController {
    private StoreItemRepository storeItemRepository;

    public StoreItemRestController(StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;
    }


    @PostMapping(value="/newStoreItem",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public StoreItem postStoreItem(@RequestBody StoreItem item){
        System.out.println(item);
        return storeItemRepository.save(item);
    }

    @GetMapping("/addStoreItem")
    public RedirectView addStoreItem(@RequestParam String name, double price){
        StoreItem itemm = new StoreItem(name,price);
        storeItemRepository.save(itemm);
        return new RedirectView("/store");
    }

    @GetMapping("/deleteStoreItem")
    public RedirectView delStoreItem(@RequestParam Long id){
        System.out.println("find by id: "+storeItemRepository.getOne(id).getName());
        storeItemRepository.delete(storeItemRepository.getOne(id));

        return new RedirectView("/store");
    }

}
