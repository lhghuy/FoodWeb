package com.example.FoodHKD.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodHKD.model.Order;
import com.example.FoodHKD.model.OrderDetail; // Ensure this import matches the actual package of OrderDetail

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    List<OrderDetail> findByOrder(Order order);
}
