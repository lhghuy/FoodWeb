package com.example.FoodHKD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodHKD.model.Order;
import com.example.FoodHKD.model.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByStatus(String status);
    List<Order> findByCreatedBy(User user);
}
