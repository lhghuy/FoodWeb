package com.example.FoodHKD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodHKD.model.FoodItem; // Ensure this is the correct package for FoodItem
import com.example.FoodHKD.model.InventoryLog; // Ensure this is the correct package for InventoryLog
import com.example.FoodHKD.model.User; // Ensure this is the correct package for User

public interface InventoryLogRepository extends JpaRepository<InventoryLog, Integer> {
    List<InventoryLog> findByFood(FoodItem food);
    List<InventoryLog> findByCreatedBy(User user);
}
