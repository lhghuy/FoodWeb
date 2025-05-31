package com.example.FoodHKD.rest.admin;

import com.example.FoodHKD.model.FoodItem;
import com.example.FoodHKD.service.CategoryService;
import com.example.FoodHKD.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/foods")
public class FoodAdminRest {
    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllFoodItems() {
        try {
            List<FoodItem> foodItems = foodItemService.getAllFoodItems();
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", foodItems);
            response.put("total", foodItems.size());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to fetch food items: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getFoodItemById(@PathVariable("id") Integer id) {
        try {
            FoodItem foodItem = foodItemService.getFoodItemById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", foodItem);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Food item not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to fetch food item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createFoodItem(@RequestBody @Valid FoodItem foodItem,
            BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", getValidationErrors(result));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            FoodItem createdFoodItem = foodItemService.createFoodItem(foodItem);
            response.put("success", true);
            response.put("message", "Food item created successfully");
            response.put("data", createdFoodItem);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to create food item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateFoodItem(@PathVariable("id") Integer id,
            @RequestBody @Valid FoodItem foodItem,
            BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("success", false);
            response.put("message", "Validation failed");
            response.put("errors", getValidationErrors(result));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        try {
            FoodItem updatedFoodItem = foodItemService.updateFoodItem(id, foodItem);
            response.put("success", true);
            response.put("message", "Food item updated successfully");
            response.put("data", updatedFoodItem);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", "Food item not found with id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to update food item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFoodItem(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        try {
            foodItemService.deleteFoodItem(id);
            response.put("success", true);
            response.put("message", "Food item deleted successfully");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Failed to delete food item: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("data", categoryService.getAllCategories());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("message", "Failed to fetch categories: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    private Map<String, String> getValidationErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }
}
