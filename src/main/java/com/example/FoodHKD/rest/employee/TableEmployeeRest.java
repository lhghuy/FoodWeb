package com.example.FoodHKD.rest.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FoodHKD.repository.TableRepository;
import com.example.FoodHKD.repository.UserRepository;
import com.example.FoodHKD.service.FoodItemService;
import com.example.FoodHKD.service.TableService;

@RestController
@RequestMapping("/api/employee/tables")
public class TableEmployeeRest {
    @Autowired
    private TableService tableService;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemService foodItemService;
}
