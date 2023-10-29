package com.geekster.RestaurantManagementService.repo;

import com.geekster.RestaurantManagementService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Integer> {
    User findFirstByUserEmail(String email);

}
