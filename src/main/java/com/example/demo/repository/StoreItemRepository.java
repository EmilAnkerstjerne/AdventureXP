package com.example.demo.repository;

import com.example.demo.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
}
