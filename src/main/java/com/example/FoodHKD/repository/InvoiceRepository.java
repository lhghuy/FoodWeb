package com.example.FoodHKD.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository; // Ensure this path matches the actual location of the Invoice class

import com.example.FoodHKD.model.Invoice; // Ensure this path matches the actual location of the Order class
import com.example.FoodHKD.model.Order;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Optional<Invoice> findByOrder(Order order);
}
