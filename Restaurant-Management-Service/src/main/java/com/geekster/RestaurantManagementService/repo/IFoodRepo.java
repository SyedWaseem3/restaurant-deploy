package com.geekster.RestaurantManagementService.repo;

import com.geekster.RestaurantManagementService.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepo extends JpaRepository<Food, Integer> {
}
