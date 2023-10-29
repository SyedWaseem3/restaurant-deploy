package com.geekster.RestaurantManagementService.service;

import com.geekster.RestaurantManagementService.model.Food;
import com.geekster.RestaurantManagementService.repo.IFoodRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    IFoodRepo foodRepo;

    public String addFood(Food newFood) {
        foodRepo.save(newFood);
        return "food item added!";
    }

    public String addListOfFoods(List<Food> newFoods) {
        foodRepo.saveAll(newFoods);
        return "list of foods added!";
    }

    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }

    @Transactional
    public void changeFood(Integer foodId, Integer newFoodId) {
        Food foodToBeChanged = foodRepo.findById(foodId).orElseThrow();
        Food foodToBeAdded = foodRepo.findById(newFoodId).orElseThrow();
        foodRepo.delete(foodToBeChanged);
        foodRepo.save(foodToBeAdded);
    }
}
