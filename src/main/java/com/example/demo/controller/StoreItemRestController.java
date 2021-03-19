package com.example.demo.controller;

import com.example.demo.model.StoreItem;
import com.example.demo.repository.StoreItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@RestController
public class StoreItemRestController {
    private final StoreItemRepository storeItemRepository;

    public StoreItemRestController(StoreItemRepository storeItemRepository) {
        this.storeItemRepository = storeItemRepository;
    }

    //POST Virker fra postman og GUI

    @PostMapping(value="/newStoreItem",consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public StoreItem postStoreItem(@RequestBody StoreItem item){
        return storeItemRepository.save(item);
    }

    //TODO: GET skal laves til visning af items også via json
    //https://stackoverflow.com/questions/24468459/sending-a-json-to-server-and-retrieving-a-json-in-return-without-jquery

    //GET Virker fra postman
    @GetMapping("/store/item/{id}")
    public ResponseEntity<StoreItem> getStoreItemById(@PathVariable Long id){
        Optional<StoreItem> item = storeItemRepository.findById(id);
        if (item.isPresent()){
            StoreItem reelItem = item.get();
            return new ResponseEntity<>(reelItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/store/item/all")
    public List<StoreItem> getAllItems(){
        return storeItemRepository.findAll();
    }

    //DEL virker fra postman
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    @DeleteMapping("/store/deleteItem/{id}")
    public void deleteActivity(@PathVariable Long id){
        storeItemRepository.delete(storeItemRepository.getOne(id));
    }


    //Funktionelt - ikke REST

    @GetMapping("/store/addStoreItem")
    public RedirectView addStoreItem(@RequestParam String name, double price){
        StoreItem itemm = new StoreItem(name,price);
        storeItemRepository.save(itemm);
        return new RedirectView("/store");
    }

    @GetMapping("/store/deleteStoreItem")
    public RedirectView delStoreItem(@RequestParam Long id){
        storeItemRepository.delete(storeItemRepository.getOne(id));
        return new RedirectView("/store");
    }



}
