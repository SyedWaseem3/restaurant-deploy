package com.geekster.RestaurantManagementService.repo;

import com.geekster.RestaurantManagementService.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin, Integer> {
}
