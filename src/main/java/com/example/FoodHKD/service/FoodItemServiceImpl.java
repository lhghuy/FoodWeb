package com.example.FoodHKD.service;

import com.example.FoodHKD.model.FoodItem;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.FoodHKD.repository.FoodItemRepository;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Integer id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
    }

    @Override
    public FoodItem saveFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public List<FoodItem> getAvailableFoodItems() {
        return foodItemRepository.findByQuantityGreaterThan(0);
    }

    @Override
    public FoodItem createFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem updateFoodItem(Integer id, FoodItem updated) {
        FoodItem foodItem = getFoodItemById(id);
        foodItem.setName(updated.getName());
        foodItem.setDescription(updated.getDescription());
        foodItem.setPrice(updated.getPrice());
        foodItem.setAnh(updated.getAnh());
        foodItem.setCategory(updated.getCategory());
        foodItem.setQuantity(updated.getQuantity());
        return foodItemRepository.save(foodItem);
    }

    @Override
    public void deleteFoodItem(Integer id) {
        FoodItem foodItem = getFoodItemById(id);

        if (foodItem.getTableDetails() != null && !foodItem.getTableDetails().isEmpty()) {
            throw new RuntimeException("Không thể xoá món ăn vì đang được sử dụng trong bàn ăn.");
        }

        foodItemRepository.deleteById(id);
    }


    @Override
    public List<FoodItem> searchFoodItems(String keyword) {
        return foodItemRepository.findByNameContainingIgnoreCase(keyword);
    }
}
