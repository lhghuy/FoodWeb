package com.example.FoodHKD.repository;

import java.util.List;
import java.util.Optional;

import com.example.FoodHKD.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FoodHKD.model.TableEntity;

public interface TableRepository extends JpaRepository<TableEntity, Integer> {
    Optional<TableEntity> findByTableNumber(String tableNumber);
    List<TableEntity> findByStatus(String status);
    List<TableEntity> findByStatusOrEmployee(String status, User employee);
}
