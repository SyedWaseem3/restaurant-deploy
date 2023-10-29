package com.geekster.RestaurantManagementService.controller;

import com.geekster.RestaurantManagementService.model.Food;
import com.geekster.RestaurantManagementService.service.FoodService;
import com.geekster.RestaurantManagementService.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {
    @Autowired
    VisitorService visitorService;

    @Autowired
    FoodService foodService;

    @GetMapping("foods")
    public List<Food> getAllFoods(){
        return foodService.getAllFoods();
    }
}
