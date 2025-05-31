package com.example.FoodHKD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodHKD.model.Category; // Ensure this path matches the actual location of FoodItem
import com.example.FoodHKD.model.FoodItem; // Ensure this path matches the actual location of Category

public interface FoodItemRepository extends JpaRepository<FoodItem, Integer> {
    List<FoodItem> findByNameContainingIgnoreCase(String keyword);
    List<FoodItem> findByCategory(Category category);
    List<FoodItem> findByQuantityGreaterThan(Integer quantity);
}
