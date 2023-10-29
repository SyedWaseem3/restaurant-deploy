package com.geekster.RestaurantManagementService.repo;

import com.geekster.RestaurantManagementService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order, Integer> {
}
