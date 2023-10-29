package com.geekster.RestaurantManagementService.repo;

import com.geekster.RestaurantManagementService.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisitorRepo extends JpaRepository<Visitor, Integer> {
}
