package com.geekster.RestaurantManagementService.controller;

import com.geekster.RestaurantManagementService.model.Food;
import com.geekster.RestaurantManagementService.model.Order;
import com.geekster.RestaurantManagementService.model.User;
import com.geekster.RestaurantManagementService.service.AdminService;
import com.geekster.RestaurantManagementService.service.FoodService;
import com.geekster.RestaurantManagementService.service.OrderService;
import com.geekster.RestaurantManagementService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    FoodService foodService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @PostMapping("addFood")
    public String addFood(@RequestBody Food newFood){
        return foodService.addFood(newFood);
    }

    @PostMapping("addFoods")
    public String addListOfFoods(@RequestBody List<Food> newFoods){
        return foodService.addListOfFoods(newFoods);
    }

    @GetMapping("users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("userById/{userId}")
    public User getUserById(@PathVariable Integer userId){
        return userService.getUserBydId(userId);
    }

    @GetMapping("orders")
    public List<Order> getOrders(){
        return orderService.getOrders();
    }
}
